package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Tb_login_outBean;
import tool.Transform;

/**
 * 登入登出表tb_login_out相关操作
 * @author zuck zhao
 *
 */
public class Tb_login_out {	
	/**
	 * 登入登出数据相关变量
	 */
	String vin = null;					//车架号
	String login_serial_number = null;	//终端登入流水号
	String logout_serial_number = null;	//终端登出流水号
	String login_time = null;			//终端登入时间
	String logout_time = null;			//终端登出时间		
	String iccid = null;				//ICCID
	String judge = "1";   				//判断位，1代表登入，0代表登出，一次查询	取出最近的两条记录，如果是判断标记位，然后返回根据不同情况返回，这样不会产生空位置
	int number = 0;  					//命令表示，用于判断是登入还是登出，等于1是登入
	
  //String server_time = null;			//服务器时间，插入数据的时间
    String message_time = null;			//报文时间，也就是报文采集时间
  //String type = null;					//类型,默认填“国标”
  //String checkout = null;				//校验，默认填“成功”
	String message_length = null;		//报文长度
	String original_message = null;		//原始报文（长度1600）
	
//	String vin = "vin";										//车架号
//	String login_serial_number = "login_serial_number";		//终端登入流水号
//	String logout_serial_number = "logout_serial_number";	//终端登出流水号
//	String login_time = null;								//终端登入时间
//	String logout_time = null;								//终端登出时间	
//	String iccid = "iccid";									//ICCID
	

	/**
	 * 有个思路是：在数据库表中，放置一个判断位，是登入就置为0，是登出就置为1，一次查询	取出最近的两条记录，如果是判断标记位，然后返回根据不同情况返回，这样不会产生空位置
	 */
	/**
	 * 保存数据
	 */
	public void insert(){
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		/**
		 * a=1认为是登录，到时候再根据具体的登录登出进行判断
		 */
		if(number == 1){
			judge = "1";//车辆登入是1
			sql = "insert into tb_login_out(judge,vin,login_serial_number,logout_serial_number,iccid,login_time,logout_time,message_time,message_length,original_message)"
					+ "values('"+judge+"','"+vin+"','"+login_serial_number+"','"+logout_serial_number+"','"+iccid+"','"+login_time+"','"+logout_time+"','"+message_time+"','"+message_length+"','"+original_message+"')";
		}else{
			judge = "4";//车辆登出是4
			sql = "insert into tb_login_out(judge,vin,login_serial_number,logout_serial_number,iccid,login_time,logout_time,message_time,message_length,original_message)"
					+ "values('"+judge+"','"+vin+"','"+login_serial_number+"','"+logout_serial_number+"','"+iccid+"','"+login_time+"','"+logout_time+"','"+message_time+"','"+message_length+"','"+original_message+"')";
		}
		String sql2 = "insert into tb_history_message(vin,iccid,message_time,message_length,original_message)" //同时进行两个操作，该操作单独保存历史报文
				+ "values('"+vin+"','"+iccid+"','"+message_time+"','"+message_length+"','"+original_message+"')";
		try {
			stmt = con.createStatement();
			stmt.execute(sql);   //插入不能使用executeQuery()
			stmt.execute(sql2);   //插入不能使用executeQuery()
			
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
			System.out.println("登入登出tb_login_out的insert执行完毕，关闭所有连接，插入成功");
		}
		
	}
	
	
	public String select2(String vin){//查询指定的vin车架号是否在线，在线的意思是登录后未登出，其他状态均为离线
		/**
		 * 数据库变量
		 */
		String state = null;//在线状态
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select judge from tb_login_out where vin ='"+vin+"' order by id desc LIMIT 2";//select * from apple order by id desc LIMIT 1   查询最近两条记录
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			/**
			 * 有两个情况：1.登入后已经登出
			 * 			  2.登入后未登出
			 */
			if(rs.next()){
				String judge = rs.getString("judge");//判断登入登出
				System.out.println("judge是："+judge);
				if(judge.equals("1")){//情况1：登入后未登出
					state = "在线";
					System.out.println("情况1：登入后未登出,state在线");
				}else if(judge.equals("4")){//情况2：登入后已登出	
					state = "离线";
					System.out.println("情况2：登入后已经登出，state离线");
				}else{//情况3：既未登入，也为登出，全部赋值null
					state = "离线";
					System.out.println("情况3：既未登入，也为登出，state离线");
				}
			}else{
				//情况3：既未登入，也为登出，全部赋值null
				state = "离线";
				System.out.println("情况3：既未登入，也为登出，state离线");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			System.out.println("登入登出tb_login_out的select2执行完毕，关闭所有连接");
		}
		return state;
	}
	/**
	 * 查询数据
	 */
	public Tb_login_outBean select(String vin){//judgeStr参数用于区分网页端查询的是登入数据还是登出数据
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Tb_login_outBean LOBean  = new Tb_login_outBean(); 
		
		String sql = "select * from tb_login_out where vin ='"+vin+"' order by id desc LIMIT 2";//select * from apple order by id desc LIMIT 1   查询最近一条记录
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			/**
			 * 有两个情况：1.登入后已经登出
			 * 			  2.登入后未登出
			 */
			if(rs.next()){
				String judge = rs.getString("judge");//判断登入登出
				System.out.println("judge是："+judge);
				if(judge.equals("1")){//情况1：登入后未登出
					LOBean.setLogout_time(null);//设置登出时间 为null
					LOBean.setLogin_time(rs.getString("login_time"));//设置登入时间
					LOBean.setVin(rs.getString("vin"));
					LOBean.setSerial_number(rs.getString("login_serial_number"));					
					LOBean.setIccid(rs.getString("iccid"));
					LOBean.setServer_time(rs.getString("server_time"));
					LOBean.setMessage_time(rs.getString("message_time"));
					LOBean.setType(rs.getString("type"));
					LOBean.setCheckout(rs.getString("checkout"));
					LOBean.setMessage_length(rs.getString("message_length"));
					LOBean.setOriginal_message(rs.getString("original_message"));
					System.out.println("情况1：登入后未登出");

				}else if(judge.equals("4")){//情况2：登入后已登出										
                    LOBean.setLogout_time(rs.getString("logout_time"));//设置登出时间
					
					rs.next();//指到第二行，登入行
					LOBean.setLogin_time(rs.getString("login_time"));//设置登入时间
					LOBean.setVin(rs.getString("vin"));
					LOBean.setSerial_number(rs.getString("login_serial_number"));					
					LOBean.setIccid(rs.getString("iccid"));
					LOBean.setServer_time(rs.getString("server_time"));
					LOBean.setMessage_time(rs.getString("message_time"));
					LOBean.setType(rs.getString("type"));
					LOBean.setCheckout(rs.getString("checkout"));
					LOBean.setMessage_length(rs.getString("message_length"));
					LOBean.setOriginal_message(rs.getString("original_message"));
					System.out.println("情况2：登入后已经登出");
				}else{//情况3：既未登入，也为登出，全部赋值null
                    LOBean.setLogout_time(null);//设置登出时间
					
					LOBean.setLogin_time(null);//设置登入时间
					LOBean.setVin(null);
					LOBean.setSerial_number(null);					
					LOBean.setIccid(null);
					LOBean.setServer_time(null);
					LOBean.setMessage_time(null);
					LOBean.setType(null);
					LOBean.setCheckout(null);
					LOBean.setMessage_length(null);
					LOBean.setOriginal_message(null);
					System.out.println("情况3：既未登入，也为登出，全部赋值null");
				}

			}
//			while(rs.next()){
//				String judge = rs.getString("judge");//判断登入登出
//				if(judgestr.equals("1")){//登入
//					if(judge.equals("1")){//选择登入信息赋值给bean
//						System.out.println("查询登入信息，给bean赋值");
//						LOBean.setLogin_time(rs.getString("login_time"));
//						LOBean.setVin(rs.getString("vin"));
//						LOBean.setSerial_number(rs.getString("login_serial_number"));
//						
//						LOBean.setIccid(rs.getString("iccid"));
//						LOBean.setServer_time(rs.getString("server_time"));
//						LOBean.setMessage_time(rs.getString("message_time"));
//						LOBean.setType(rs.getString("type"));
//						LOBean.setCheckout(rs.getString("checkout"));
//						LOBean.setMessage_length(rs.getString("message_length"));
//						LOBean.setOriginal_message(rs.getString("original_message"));
//					}
//				}else{//登出
//					if(judge.equals("0")){//选择登出信息赋值给bean
//						System.out.println("查询登出信息，给bean赋值");
//						LOBean.setLogout_time(rs.getString("logout_time"));
//
//						LOBean.setVin(rs.getString("vin"));
//						LOBean.setSerial_number(rs.getString("login_serial_number"));
//						LOBean.setIccid(rs.getString("iccid"));
//						LOBean.setServer_time(rs.getString("server_time"));
//						LOBean.setMessage_time(rs.getString("message_time"));
//						LOBean.setType(rs.getString("type"));
//						LOBean.setCheckout(rs.getString("checkout"));
//						LOBean.setMessage_length(rs.getString("message_length"));
//						LOBean.setOriginal_message(rs.getString("original_message"));
//					}
//				}
//			}
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
			System.out.println("登入登出tb_login_out的select执行完毕，关闭所有连接");
		}
		return LOBean;
	}
	
	/**
	 * 从数据包找出相应的数据，并且赋值给相应的变量
	 */
	public void analysis(String str){
		
		String RemoveBlank = str.replace(" ", "");//去除空格
		
		/**
		 * 获取原始十六进制
		 */
		String minglingbiaozhi = RemoveBlank.substring(4,6);//用于判断，只有登入操作才有iccid，即判断为01时候，才执行iccid查询
		if(minglingbiaozhi.equals("01")){//登入时候为01，即判断为01时候，才执行iccid查询
			String Stringiccid = RemoveBlank.substring(64, 104);//iccid赋值
			char[] chariccid = change(Stringiccid); //对iccid进行转换
			for(int i=0 ;i<20;i++){
				if(i == 0 ){
					iccid = String.valueOf(chariccid[0]);
				}else{
					iccid = iccid+chariccid[i];
				}
				
			}
		}
		
		String Stringvin = RemoveBlank.substring(8, 42);//vin赋值
		char[] charvin = change(Stringvin); //对vin进行转换
		for(int i=0 ;i<17;i++){
			if(i == 0 ){
				vin = String.valueOf(charvin[0]);
			}else{
				vin = vin+charvin[i];
			}
			
		}
		
		//vin = RemoveBlank.substring(8, 42);//车架号赋值
		minglingbiaozhi = RemoveBlank.substring(4,6);
		String shujucaijishijian = RemoveBlank.substring(48, 60); //数据采集时间
		String liushuihao = RemoveBlank.substring(60, 64); //流水号（分为登入和登出）
		//iccid = RemoveBlank.substring(64, 104);										//iccid赋值
		
		
		number = Integer.valueOf(minglingbiaozhi,16);								//登入登出判别值赋值		
		if(number == 1){//判断是登入还是登出
			login_serial_number = Integer.valueOf(liushuihao,16).toString();		//终端登入流水号赋值，已经变成十进制			
			login_time = new Transform().Time(shujucaijishijian);//终端登入时间
		}else{
			logout_serial_number = Integer.valueOf(liushuihao,16).toString();		//终端登出流水号赋值，已经变成十进制			
			logout_time = new Transform().Time(shujucaijishijian);//终端登出时间
		}
		
		String shujucaijishijian2 = RemoveBlank.substring(48, 60); //数据采集时间
		message_time = new Transform().Time(shujucaijishijian);//数据采集时间
		message_length = Integer.toString(RemoveBlank.length()/2); //报文长度,求得是字节长度，所以要除以2
		original_message = RemoveBlank;//原始报文赋值（去除空格后的值）		
		
		System.out.println("====================================↓↓↓↓历史报文Tb_history_message的analysis以下是赋值以后的数==========================================");
		System.out.println("车架号vin："+vin);
		System.out.println("报文时间message_time："+message_time);
		System.out.println("报文长度message_length："+message_length);
		System.out.println("原始报文original_message："+original_message);
		
		System.out.println("=====================================↓↓↓↓以下为登录登出Tb_login_out的analysis赋值后的变量值====================");
		System.out.println("iccid:"+iccid);
		System.out.println("number:"+number);
		System.out.println("login_serial_number:"+login_serial_number);
		System.out.println("logout_serial_number:"+logout_serial_number);
		System.out.println("login_time:"+login_time);
		System.out.println("logout_time:"+logout_time);//
		System.out.println("=====================================↑↑↑↑为登录登出Tb_login_out的analysis赋值后的变量值========================");
				
		System.out.println("====================================↑↑↑↑历史报文Tb_history_message的analysis以下是赋值以后的数==========================================");

		


	}
	
	/**
	 * 目的：将40位iccid变成20位字符
	 * @param s
	 * @return
	 */
	public static char[] change(String s) {
		if(s.startsWith("0x")){
			s = s.substring(2);
		}
		char[] result = new char[s.length()/2]; // 十六进制是两位，字节数组的长度就是字符串长度的一半
		for(int i=0;i<result.length;i++)
			result[i] = (char)(Integer.parseInt(s.substring(i*2,i*2+2),16 & 0xFF)); // 16 & 0xff 功能将字符串转换成十六进制
		return result; // 返回字节数组
	}
	
//	/**
//	 * 给登入流水号变量login_serial_number赋值
//	 * 1.因为登出流水号和当次登入流水号一致，所以可以查询最近的一次登出流水号，
//	 * 2.然后在此基础上加1则变成了此次登入流水号,把值赋给login_serial_number变量
//	 */
//	public void Login_serial_number(){
//		/**
//		 * 数据库变量
//		 */
//		Connection con = ConnectionManager.getInstance().getConnection();
//		Statement stmt = null;
//		ResultSet rs = null;
//		String sql = "select logout_serial_number from tb_login_out order by Id desc LIMIT 1";//查询最近一条记录
//		try {
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(sql);   
//			if(rs.next()){
//				String logout_serial_number2 =rs.getString("logout_serial_number");  //获取最近一次登出流水号
//				try {
//					//以下将字符串转为int,加一之后转为string赋值给登入流水号变量
//		            int a = Integer.parseInt(logout_serial_number2);
//		            int b = a + 1;
//		            login_serial_number = Integer.toString(b);  //赋值
//					System.out.println("成功给登入流水号变量赋值,且值为:"+login_serial_number);
//		        } catch (NumberFormatException e) {
//		            e.printStackTrace();
//		        }
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			try{
//				if(rs != null){
//					rs.close();
//				}
//				if(stmt != null){
//					stmt.close();
//				}
//				if (con != null){
//					con.close();
//				}
//				}catch (Exception e){
//					e.printStackTrace();
//				}
//		}
//	}
	
//	/**
//	 * 给登出流水号赋值
//	 */
//	public void Logout_serial_number(){
//		/**
//		 * 数据库变量
//		 */
//		Connection con = ConnectionManager.getInstance().getConnection();
//		Statement stmt = null;
//		ResultSet rs = null;
//		String sql = "select login_serial_number from tb_login_out order by Id desc LIMIT 1";//查询最近一条记录
//		try {
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(sql);   
//			if(rs.next()){
//				logout_serial_number =rs.getString("login_serial_number");  //获取最近一次登入流水号，并且给logout_serial_number赋值
//				System.out.println("方法Logout_serial_number获取的logout_serial_number是:"+logout_serial_number);
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			try{
//				if(rs != null){
//					rs.close();
//				}
//				if(stmt != null){
//					stmt.close();
//				}
//				if (con != null){
//					con.close();
//				}
//				}catch (Exception e){
//					e.printStackTrace();
//				}
//		}
//	}
	
}

