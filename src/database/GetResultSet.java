package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetResultSet {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public ResultSet getResultSet(){
		String number = null;
		//String sql = "select * from zuobiao where id = '"+ number +"'; ";
		String sql2 = "insert into tb_history_message(vin,server_time,type,checkout,message_length,original_message) values(a,b,c,d,e,f)";
		con = ConnectionManager.getInstance().getConnection();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if (con != null){
					con.close();
				}
				}catch (Exception e){
					e.printStackTrace();
				}
		}
		System.out.println("测试：插入一条记录");
		return rs;
	}
	
	public void insertResultSet(){
		String sql2 = "insert into tb_history_message(vin,server_time,type,checkout,message_length,original_message) values('a','b','c','d','e','f');";
		con = ConnectionManager.getInstance().getConnection();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if (con != null){
					con.close();
				}
				}catch (Exception e){
					e.printStackTrace();
				}
		}
		System.out.println("测试：插入一条记录");
	}
	
	public void select(){
		String sql2 = "select * from tb_history_message;";
		con = ConnectionManager.getInstance().getConnection();
		String testStr = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql2);
			rs.next();
			testStr = rs.getString(3);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if (con != null){
					con.close();
				}
				}catch (Exception e){
					e.printStackTrace();
				}
		}
		System.out.println("测试：获取数据:"+testStr);
	}
}
