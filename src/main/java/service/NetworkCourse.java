package service;

import java.sql.SQLException;
//import java.sql.Types;
import java.util.List;

import org.apache.commons.io.filefilter.TrueFileFilter;

import dao.CourseDao;
import dao.TypesDao;
import dao.UsersDao;
import moddel.Comment;
import moddel.Course;
import moddel.Users;
import moddel.Types; 
public class NetworkCourse {
	public void addUsers(Users g){//增加一条用户信息
		UsersDao dao=new UsersDao();
		try {
			dao.addUsers(g);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(int id) {//删除一条用户信息
		UsersDao dao=new UsersDao();
		try {
			dao.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(Users g) {//更新一条用户信息
		UsersDao dao=new UsersDao();
		try {
			dao.update(g);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Users query(int id) {//查询一条用户信息
		UsersDao dao=new UsersDao();
	    Users g=null;
		try {
			g = dao.query(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return g;
	}
	public Users query1(String name) throws SQLException {//通过名称查询一条用户信息
		UsersDao dao=new UsersDao();
	    Users g=null;
		try {
			g = dao.query1(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return g;
	}
	public Users login(String name,String password){
		UsersDao dao=new UsersDao();
		Users g=null;
		try {
			g = dao.login(name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
	}
	public void  addCourse(Course c) {// 卖家上架一门课程
		CourseDao dao=new CourseDao();
		dao.addCourse(c);
	}
	public List<Course> select(int page, int record, String issure ) {// 卖家查询出自己发布的课程数据并分页(课程为综合时)
		CourseDao dao=new CourseDao();
		List<Course> c=dao.select(page, record, issure);
		return c;
	}	
	public List<Course> select1(int page, int record,int id,String issure) {// 卖家查询出自己发布的课程数据并分类分页
		CourseDao dao=new CourseDao();
		List<Course> c=dao.select1(page, record,id, issure);
		return c;
	}
	public int getCount(int record,String issure) { // 卖家针对自己发布的课程获得分页总页数（只分页，不分种类）
		CourseDao dao=new CourseDao();
		int count=dao.getCount(record,issure);
		return count;
	}
	public int getCount1(int record ,int id,String issure) {  //卖家针对自己发布的课程获得分类分页总页数
		CourseDao dao=new CourseDao();
		int count=dao.getCount1(record,id,issure);
		return count;
	}
	public List<Types> QueryType() { // 查询所有课程类型
		TypesDao dao=new TypesDao();
		return dao.query();
	}
	public boolean recharge(Object[] param){
		UsersDao uDao=new UsersDao();
		int isSucceed=-1;
		try {
			isSucceed =uDao.recharge(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isSucceed ==1){
			return true;
		}else{
			return false;
		}
	}
	public int addToShopCar(Object[] param){
		CourseDao cdDao =new CourseDao();
	    int isSuceed=-1;
	    try{
			isSuceed =cdDao.addToShopCar(param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return isSuceed ;
	    
	}
	public void buyCourseFromShopCar(int[] arrayId,int userId,double restMoney) throws SQLException{
		CourseDao cDao=new CourseDao();
		cDao.reSetMoney(userId, restMoney);
		for(int i=0;i<arrayId.length&&arrayId[i]!=0;i++){
			Course cs=cDao.selectCourseSetIntoShopCar(arrayId[i]);
			System.out.println(cs);
			cs.setIsSelect(userId);
			cDao.addCourse_shopCar(cs);
		}
	}
	public List<Course> selectC(int page, int record) {// 买家主页查询出所有课程数据并分页
		CourseDao dao=new CourseDao();
		List<Course> c=dao.selectC(page, record);
		return c;
	}
	public int getCount2(int record) { // 买家获得分页总页数（只分页，不分种类，即查询出所有课程时）
		CourseDao dao=new CourseDao();
		int count=dao.getCount2(record);
		return count;
	}
	public List<Course> select2(int page, int record,String coursetype) {// 买家查询出数据并分类分页（即买家主页的分类课程数据）
		CourseDao dao=new CourseDao();
		List<Course> c=dao.select2(page, record, coursetype);
		return c;
	}
	public int getCount3(int record ,String coursetype) {  //买家主页获得分类分页总页数
		CourseDao dao=new CourseDao();
		int count=dao.getCount3(record,coursetype);
		return count;
	}
	public Course selectDetail(int id) {// 查询单门课程的全部信息
		CourseDao dao=new CourseDao();
		return dao.selectDetail(id);
	}
	public List<Comment> selectComment(int id) {// 查询单门课程的评论信息
		CourseDao dao=new CourseDao();
		return dao.selectComment(id);
	}
	public Course queryBuy(int id ,int id1) {// 查询用户是否购买过此门课程
		CourseDao dao=new CourseDao();
		return dao.queryBuy(id, id1);
	}
	public void buy(int id,int id1){//买家购买时，对课程的操作
		CourseDao dao=new CourseDao();
		Course c=dao.selectDetail(id);
		c.setIsSelect(id1);
		dao.addCourse1(c);
	}
	public List<Course> selectlike(String name) {// 模糊查询查询出一类课程
		CourseDao dao=new CourseDao();
		List<Course> c=dao.selectlike(name);
		return c;
	}
	public void  addcomment(Comment c) {// 为一门课程写评论
		CourseDao dao=new CourseDao();
		dao.addcomment(c);
	}
}

