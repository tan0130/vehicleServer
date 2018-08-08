package testDeom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabases {
	public  Connection getConnection() {
		/*
		 * 连接国标数据库，返回一个数据库连接
		 * android_db数据库中有多个数据表
		 */
		Connection con = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/national_standard_test?useSSL=true&characterEncoding=utf8","root","zhaozhihua");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
