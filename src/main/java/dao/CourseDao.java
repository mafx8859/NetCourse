package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.impl.NewPooledConnection;

import moddel.Comment;
import moddel.Course;
import moddel.shopCarBean;

public class CourseDao {
	public int getMaxId() throws SQLException{
	    Connection conn=JDBCUtil.getDataSource().getConnection();
		QueryRunner qr = new QueryRunner();
		String sql="select max(id) from course";
		int id=(int) qr.query(conn,sql, new ScalarHandler());
		conn.close();
		return id;
	}
	public void  addCourse(Course c) {// 卖家上架一门课程
		//让产生一个question编号
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "insert into course (courseName,issure,video,type,image,alt,price) values(?,?,?,?,?,?,?)";
		String sql1 = "select max(id) from course";
		int id=-1;
		try {
			Object[] params = { c.getCourseName(), c.getIssure(), c.getVideo(), c.getType(), c.getImage(), c.getAlt(),
					c.getPrice() };
			qr.update(sql, params);
			id = (int) qr.query(sql1, new ScalarHandler());
			String sql2 = "update course set question=" + id + " where id= " + id;
			qr.update(sql2);

		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
 
	}
	public void  addCourse_shopCar(Course c) throws SQLException {// 卖家上架一门课程
		//让产生一个question编号
		Connection connection=JDBCUtil.getDataSource().getConnection();
		QueryRunner qr = new QueryRunner();
		String sql = "insert into course (courseName,issure,video,type,image,alt,isSelect,price,question) values(?,?,?,?,?,?,?,?,?)";
		Object[] param={c.getCourseName(), c.getIssure(), c.getVideo(), c.getType(), c.getImage(), c.getAlt(),
				c.getIsSelect(),c.getPrice(),c.getQuestion()};
       qr.insert(connection,sql,new BeanHandler<Course>(Course.class),param);
	}
    public List<Course> selectMyCourse(int id) throws SQLException{
    	String sql="SELECT * FROM course WHERE isSelect="+id+"";
    	Connection connection=JDBCUtil.getDataSource().getConnection();
    	QueryRunner qRunner=new QueryRunner();
    	List<Course> list=qRunner.query(connection,sql, new BeanListHandler<Course>(Course.class));
    	return list;
    }
	public List<Course> select(int page, int record,String issure) {// 查询出数据并分页(课程为综合时)
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			String sql = "select * from course where 1=1 and isSelect=0 and isCart=0 and issure ="+"'"+issure+"'"+" order by id desc limit ?,? ";
			System.out.println(sql);
			Object[] params = { (page - 1) * record, record };
			List<Course> c = qr.query(sql, new BeanListHandler<Course>(Course.class), params);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public List<Course> select1(int page, int record,int id, String issure) {// 查询出数据并分类分页
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			String sql="select * from course where 1=1 and isSelect=0 and isCart=0 and issure= "+"'"+issure+"'"+" and type=(select type from coursetype where id=?) order by id desc limit ?,?";
			System.out.println(sql);
			Object[] params = { id,(page - 1) * record, record };
			List<Course> c = qr.query(sql, new BeanListHandler<Course>(Course.class), params);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public Course selectCourseSetIntoShopCar(int courseId) throws SQLException{
		Connection conn=JDBCUtil.getDataSource().getConnection();
		QueryRunner qr = new QueryRunner();
		String sql="select * from course where id=?";
		Course cs=qr.query(conn,sql,new BeanHandler<Course>(Course.class),courseId);
		conn .close();
		return cs;
	} 
	public int getCount(int record,String issure) { // 获得分页总页数（只分页，不分种类）
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = -1;
		try {
			con = JDBCUtil.getConnection();// 第一步连接数据库
			// 第二步书写sql语句
			String sql = "select count(*) from course where 1=1 and issure= "+"'"+issure+"'";
			System.out.println(sql);
			ps = con.prepareStatement(sql);// 第三步：预编译
			// 第几页需要设置好是页数减一乘以每页的记录数即是第多少页

			// 第四步执行sql
			rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
				n = (int) Math.ceil(1.0 * n / record);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源，避免出现异常
			try {
				rs.close();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return n;
	}
	public int getCount1(int record ,int id, String issure) {  //获得分类分页总页数
	       Connection con=null;
	       PreparedStatement ps=null;
	       ResultSet rs=null;
	       int n=-1;
	       try {
	          con=JDBCUtil.getConnection();//第一步连接数据库
	           //第二步书写sql语句
	           String sql="select count(*) from course where 1=1 and issure ="+"'"+issure+"'"+" and type=(select type from coursetype where id=?)";
	          ps=con.prepareStatement(sql);//第三步：预编译
	          //第几页需要设置好是页数减一乘以每页的记录数即是第多少页
	         ps.setInt(1, id);
	          
	          //第四步执行sql
	          rs=ps.executeQuery();
	         
	          if(rs.next()){
	            n=rs.getInt(1);
	            n=(int)Math.ceil(1.0*n/record);
	          }
	         
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }finally{
	          //关闭资源，避免出现异常
	    	  try {
						rs.close();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	     }
	     
	      return n;
	 }
	public int addToShopCar(Object[] param) throws SQLException{
		Connection connection=JDBCUtil.getDataSource().getConnection();
		String sql1="SELECT * FROM shopCar where shopId=?";
		String sql2="INSERT INTO shopCar(userId,shopId,setTime)VALUES(?,?,?)";
	    int isSucceed=-1;
	    QueryRunner qr=new QueryRunner();
        if(qr.query(connection,sql1,new BeanHandler<Course>(Course.class),param[1])!=null){
			return isSucceed;//返回-1时说明购物车中已经存在这门课程
		}
	    isSucceed=qr.update(connection, sql2,param);
	    connection.close();
	    return isSucceed ;//放回1时间说明添加成功，返回0时说明添加失败
	}
	public List<Course> selectShopCar(int userId) throws SQLException{
		Connection connection=JDBCUtil.getDataSource().getConnection();
		shopCarBean sCar=new shopCarBean();
		List<Course> shopCourses=new ArrayList<Course>();
		QueryRunner qr=new QueryRunner();
		String sql1="select * from shopCar where userId=?";
		String sql2="select * from course where id=?";
		//查出用户所有加入购物车的课程的id
		List<shopCarBean > shopId=qr.query(connection,sql1,new BeanListHandler<shopCarBean>(shopCarBean .class),userId);
		//通过id查出课程
		for(shopCarBean sBean :shopId){
			shopCourses.add(qr.query(connection,sql2,new BeanHandler<Course>(Course.class ),sBean .getShopId()));
		}
		return shopCourses ;
	}
	public int delShopCar(int id) throws SQLException{
		Connection connection=JDBCUtil.getDataSource().getConnection();
		QueryRunner qr=new QueryRunner();
		String sql="delete from shopCar where shopId=?";
		int isSucceed=-1;
		isSucceed=qr.update(connection ,sql,id);
		connection.close();
		return isSucceed ;
	}
	public void reSetMoney(int userId,double money) throws SQLException{
		Connection connection=JDBCUtil.getDataSource().getConnection();
		QueryRunner qr=new QueryRunner();
		Object[] param={money,userId};
		String sql="update users set money=? where id=?";
		qr.update(connection, sql,param);
		connection.close();
	}
	public List<Course> selectC(int page, int record) {// 买家主页查询出所有课程数据并分页
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			String sql = "select * from course where 1=1 and isSelect=0 and isCart=0 order by id desc limit ?,? ";
			
			Object[] params = { (page - 1) * record, record };
			List<Course> c = qr.query(sql, new BeanListHandler<Course>(Course.class), params);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public int getCount2(int record) { // 买家获得分页总页数（只分页，不分种类，即查询出所有课程时）
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = -1;
		try {
			con = JDBCUtil.getConnection();// 第一步连接数据库
			// 第二步书写sql语句
			String sql = "select count(*) from course";
			
			ps = con.prepareStatement(sql);// 第三步：预编译
			// 第几页需要设置好是页数减一乘以每页的记录数即是第多少页

			// 第四步执行sql
			rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
				n = (int) Math.ceil(1.0 * n / record);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源，避免出现异常
			try {
				rs.close();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return n;
	}
	public List<Course> select2(int page, int record,String coursetype) {// 买家查询出数据并分类分页（即买家主页的分类课程数据）
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			if(page<=0){
				page=1;
			}
			String sql="select * from course where 1=1 and isSelect=0 and isCart=0 and type= ? order by id desc limit ?,?";
			
			Object[] params = {coursetype,(page - 1) * record, record};
			
			List<Course> c = qr.query(sql, new BeanListHandler<Course>(Course.class), params);
			if(c==null) return null;
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public int getCount3(int record ,String coursetype ) {  //买家主页获得分类分页总页数
	       Connection con=null;
	       PreparedStatement ps=null;
	       ResultSet rs=null;
	       int n=-1;
	       try {
	          con=JDBCUtil.getConnection();//第一步连接数据库
	           //第二步书写sql语句
	           String sql="select count(*) from course where 1=1  and type =?";
	          ps=con.prepareStatement(sql);//第三步：预编译
	          //第几页需要设置好是页数减一乘以每页的记录数即是第多少页
	         ps.setString(1, coursetype);
	          
	          //第四步执行sql
	          rs=ps.executeQuery();
	         
	          if(rs.next()){
	            n=rs.getInt(1);
	            n=(int)Math.ceil(1.0*n/record);
	          }
	         
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }finally{
	          //关闭资源，避免出现异常
	    	  try {
						rs.close();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	     }
	     
	      return n;
	 }
	public Course selectDetail(int id) {// 查询单门课程的全部信息
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			String sql = "select * from course where id ="+id;
			
			Course c = qr.query(sql,new BeanHandler<Course>(Course.class));
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public List<Comment> selectComment(int id) {// 查询单门课程的评论信息
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			String sql = "select * from comment where class1= "+id;
			 List<Comment> c = qr.query(sql,new BeanListHandler<Comment>(Comment.class));
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public void  addcomment(Comment c) {// 为一门课程写评论
		//让产生一个question编号
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "insert into comment (content,class1,issureName) values(?,?,?)";	
		try {
			Object[] params = { c.getContent(), c.getClass1(),c.getIssureName()};
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
 
	}
	public Course queryBuy(int id ,int id1) {// 查询用户是否购买过此门课程
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			
			String sql = "select * from course where 1=1 and isSelect ="+id1+" and id="+id+"";
			
			Course c = qr.query(sql,new BeanHandler<Course>(Course.class));
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public List<Course> selectlike(String name) {// 模糊查询查询出一类课程
		try {
			QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
			String sql = "select * from course where 1=1 and isSelect=0 and isCart=0 and courseName like '%"+name+"%'";
			List<Course> c = qr.query(sql, new BeanListHandler<Course>(Course.class));
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public void  addCourse1(Course c) {// 买家购买时增加新数据
		//让产生一个question编号
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "insert into course (courseName,issure,video,type,image,alt,price,isSelect,question) values(?,?,?,?,?,?,?,?,?)";
		
		try {
			Object[] params = { c.getCourseName(), c.getIssure(), c.getVideo(), c.getType(), c.getImage(), c.getAlt(),
					c.getPrice(),c.getIsSelect(),c.getQuestion()};
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
 
	}
}

