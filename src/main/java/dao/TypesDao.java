package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import moddel.Course;
import moddel.Types;
public class TypesDao {
	public void addType(String type) {// 增加一条新闻类型
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		String sql = "insert into coursetype (type) values(" + type + ")";
		try {

			qr.update(sql);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	public List<Types> query() { // 查询所有类型
		QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
		List<Types> t = new ArrayList<Types>();
		String sql = "select * from coursetype ";
		try {
			t = qr.query(sql, new BeanListHandler<Types>(Types.class));
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return t;
	}
}
