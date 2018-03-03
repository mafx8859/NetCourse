package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import moddel.Question;
import moddel.Users;



public class UsersDao {
	public void addUsers(Users g) throws SQLException {//增加一条用户信息
		Connection conn=JDBCUtil.getDataSource().getConnection();
	    QueryRunner qr = new QueryRunner();
	    String sql = "insert into users (name,password,type,money,portrait,sex,age) values(?,?,?,?,?,?,?)";
	    try {
	    	 Object[] params = {g.getName(),g.getPassword(),g.getType(),g.getMoney(),g.getPortrait(),g.getSex(),g.getAge()};
	 	     qr.update(conn,sql, params);
		} catch (SQLException e) {
			  e.printStackTrace();// TODO: handle exception
		}finally{
			conn.close();
		}
	   
	}
	public void delete(int id) throws SQLException {//删除一条用户信息
		Connection conn=JDBCUtil.getDataSource().getConnection();
	    QueryRunner qr = new QueryRunner();
	    String sql = "DELETE FROM users WHERE id=?";
	    try {
	    	Object[] params = {id};
		    qr.update(conn,sql, params);
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally{
			conn.close();
		}
	}
	public void update(Users g) throws SQLException {//更新一条用户信息
		Connection conn=JDBCUtil.getDataSource().getConnection();
	    QueryRunner qr = new QueryRunner();
	    String sql = "UPDATE users SET name=?,password=?,type=?,money=?,portrait=?,sex=?,age=? WHERE id=?";
	    try {
	    	Object[] params = {g.getName(),g.getPassword(),g.getType(),g.getMoney(),g.getPortrait(),g.getSex(),g.getAge(),g.getId()};
	 	    qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally{
			conn.close();
		}
	   
	}
	public Users query(int id) throws SQLException {//通过id查询一条用户信息
		    Connection conn=JDBCUtil.getDataSource().getConnection();
		    QueryRunner qr = new QueryRunner();
		    String sql = "SELECT * FROM users WHERE id=?";
		    try {
		       Object[] params = {id};
			   Users user=(Users)qr.query(conn,sql,new BeanHandler<Users>(Users.class),params);
			   return user;
			} catch (SQLException e) {
				e.printStackTrace();// TODO: handle exception
			}finally{
				conn.close();
			}
			return null;
		   
	}
	public Users query1(String name) throws SQLException {//通过名称查询一条用户信息
	    Connection conn=JDBCUtil.getDataSource().getConnection();
	    QueryRunner qr = new QueryRunner();
	    String sql = "SELECT * FROM users WHERE name=?";
	    try {
	       Object[] params = {name};
		   Users user=(Users)qr.query(conn,sql,new BeanHandler<Users>(Users.class),params);
		   return user;
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally{
			conn.close();
		}
		return null;
	   
}
	public Users login(String name,String password) throws SQLException{
		
		Connection conn=JDBCUtil.getDataSource().getConnection();
		QueryRunner qr = new QueryRunner();
		Users user=null;
	    String sql = "SELECT * FROM users WHERE name=? and password=?";
	    try {
	       Object[] params = {name,password};
		   user=(Users)qr.query(conn,sql,new BeanHandler<Users>(Users.class),params);
		   return user;
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally{
			conn.close();
		}
		return user;
	}
	public int setQuestion(Question q) throws SQLException{
		int isSucceed=0;
		Connection conn=JDBCUtil.getDataSource().getConnection();
		QueryRunner qr = new QueryRunner();
		String sql="INSERT INTO question(quesDec,selectA,selectB,selectC,selectD,solution,classes) VALUES(?,?,?,?,?,?,?)";
		Object[] parm={q.getQuesDec(),q.getSelectA(),q.getSelectB(),q.getSelectC(),q.getSelectD(),q.getSolution(),q.getClasses()};
		isSucceed=qr.update(conn,sql,parm);
		return isSucceed;
	}
	public ArrayList<Question> selectQuestion(int id) throws SQLException{
		int[] param={id};
		ArrayList<Question> list=new ArrayList<Question>();
		Connection conn=JDBCUtil.getDataSource().getConnection();
		QueryRunner qr = new QueryRunner();
		String sql="SELECT * FROM question WHERE classes=?";
		try {
			list=(ArrayList<Question>) qr.query(conn, sql, new BeanListHandler<Question>(Question.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.close();
		}
		return list;
	}
	public int recharge(Object[] param) throws SQLException{
		Connection conn=JDBCUtil.getDataSource().getConnection();
		QueryRunner qRunner=new QueryRunner();
		String sql="update users set money=? where id=?";
		int isSucceed=qRunner.update(conn, sql,param);
		return isSucceed;
	}
}
