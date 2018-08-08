package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tool.Transform;

/**
 * 和历史报文表tb_history_message相关的操作
 * @author zuck zhao
 *
 */
public class Tb_history_message {
	
	
	/**
	 * 历史报文数据相关变量
	 */
	String vin = "-";   //车架号vin
	String iccid ;				//iccid
    String server_time = "-";		//服务器时间，插入数据的时间
    String message_time = "-";		//报文时间，也就是报文采集时间
    String type = "国标";				//类型,默认填“国标”
    String checkout = "成功";			//校验，默认填“成功”
	String message_length = "-";	//报文长度
	String original_message = "-";	//原始报文（长度1600）
	String vehicle_his = "-";	 //整车历史报文段
	String motor_his = "-";	//电机历史报文段
	String location_his = "-";	//位置历史报文段
	String extreme_his = "-";	//极值历史报文段
	String alert_his = "-";	//报警历史报文段
	String voltage_his = "-";	//电压历史报文段
	String temperature_his = "-";	//温度历史报文段
	
/*	
	String vin = "vin";				//车架号
  //String server_time = null;		//服务器时间
	String message_time = "message_time";				//类型
	String type = "type";				//类型
	String checkout = "checkout";			//校验
	String message_length = "message_length";	//报文长度
	String original_message = "original_message";	//原始报文（长度1600）
	
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
		
		String sql = "insert into tb_history_message(vin,message_time,message_length,original_message,vehicle_his,motor_his,location_his,extreme_his,alert_his,voltage_his,temperature_his)"
						+ "values('"+vin+"','"+message_time+"','"+message_length+"','"+original_message+"','"+vehicle_his+"','"+motor_his+"','"+location_his+"','"+extreme_his+"','"+alert_his+"','"+voltage_his+"','"+temperature_his+"')";//因为在数据库已经默认了国标和成功，因此这两个字段无需再插入了
		try {
			stmt = con.createStatement();
			stmt.execute(sql);   //插入不能使用executeQuery()
			
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
			System.out.println("历史报文Tb_history_message的insert执行完毕，关闭所有连接，插入成功");
		}
		
	}
	
	/**
	 * 查询数据
	 * vin：查询车架号
	 * date1：起始日期
	 * date2：截止日期
	 */
	public String select(String vin,String date1,String date2){
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String json = null;   //返回的多条json记录
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//转换日期格式
		
		String sql = "select * from tb_history_message where vin='"+vin+"' and message_time between'"+date1+"'  and  '"+date2+"' order by id desc LIMIT 100;";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);   //插入不能使用executeQuery()
			int total = 0; //用来统计总共有多少行记录
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				total++;
				this.vin = "\""+rs.getString("vin")+"\"";
				Timestamp server_timeStr = rs.getTimestamp("server_time");
				Timestamp message_timeStr = rs.getTimestamp("message_time");	
				
				server_time = "\""+sdf.format(server_timeStr)+"\"";//涉及到一个时间格式的转换，去掉秒后面的零
				message_time = "\""+sdf.format(message_timeStr)+"\"";
				type ="\""+rs.getString("type")+"\"";
				checkout = "\""+rs.getString("checkout")+"\"";
				message_length = "\""+rs.getString("message_length")+"\"";
				original_message = "\""+rs.getString("original_message")+"\"";
				vehicle_his = "\""+rs.getString("vehicle_his")+"\"";;	 //整车历史报文段
				motor_his = "\""+rs.getString("motor_his")+"\"";;	//电机历史报文段
				location_his = "\""+rs.getString("location_his")+"\"";;	//位置历史报文段
				extreme_his = "\""+rs.getString("extreme_his")+"\"";;	//极值历史报文段
				alert_his = "\""+rs.getString("alert_his")+"\"";;	//报警历史报文段
				voltage_his = "\""+rs.getString("voltage_his")+"\"";;	//电压历史报文段
				temperature_his = "\""+rs.getString("temperature_his")+"\"";;	//温度历史报文段
				String element ="{\"vin\":"+this.vin+",\"servertime\":"+server_time+",\"codetime\":"+message_time+",\"codestyle\":"+type+",\"codecheck\":"+checkout+",\"codelength\":"+message_length+",\"originalcode\":"+original_message+",\"vehicle\":"+vehicle_his+",\"motor\":"+motor_his+",\"location\":"+location_his+",\"extreme\":"+extreme_his+",\"alert\":"+alert_his+",\"voltage\":"+voltage_his+",\"temperature\":"+temperature_his+"}";
				list.add(element); //增加元素
			}
			String listString = list.toString();
			String totalString = "\""+total+"\"";
			json = "{\"total\":"+totalString+",\"rows\":"+listString+"}";
			System.out.println("执行Tb_history_message的select获取数据");
			
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
			System.out.println("历史报文Tb_history_message的select执行完毕，关闭所有连接");
		}
		return json;
	}
	
	/**
	 * 从数据包找出相应的数据，并且赋值给相应的变量
	 */
	public void analysis (String currentTime)throws StringIndexOutOfBoundsException{
		
		
		System.out.println("==============================准备执行Tb_history_message的Tb_history_message...............=============================");
		String RemoveBlank = currentTime.replace(" ", "");//去除空格
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
		String shujucaijishijian = RemoveBlank.substring(48, 60); //数据采集时间
		message_time = new Transform().Time(shujucaijishijian);//数据采集时间
		message_length = Integer.toString(RemoveBlank.length()/2); //报文长度,求得是字节长度，所以要除以2
		original_message = RemoveBlank;//原始报文赋值（去除空格后的值）
		
		/**
		 * 以下判断移位
		 */
		String chongdianzhuangtai = RemoveBlank.substring(64,66);	//充电状态，用于判断移位
		String dianjigeshu = RemoveBlank.substring(104,106);	//电机个数，用于判断移位
		int m = 0;//数据位偏移位数
		
		System.out.println("充电状态:"+chongdianzhuangtai+",电机个数："+dianjigeshu);
		if(chongdianzhuangtai.equals("01")){//停车状态，没有电机数据，移位-28，即往前移位28位，共14个字节
			m = -28;
			vehicle_his = RemoveBlank.substring(60, 102);	 //整车历史报文段
			motor_his = "";	//电机历史报文段
			location_his = RemoveBlank.substring(130+m, 150+m);	   //位置历史报文段
			extreme_his = RemoveBlank.substring(150+m, 180+m);	   //极值历史报文段
			alert_his = RemoveBlank.substring(180+m, 200+m);	   //报警历史报文段
			
			
			String m2Str = RemoveBlank.substring(214+m, 218+m);  //单体电池总数的报文段，用于判断单体电池个数，目的是计算偏移以及电压历史报文的长度
			String m2Str2 = RemoveBlank.substring(218+m, 222+m);  //本帧起始电池序号，用于判断在第几个包中，便于确定位移
			System.out.println("单体电池个数十六进制数据："+m2Str);
			System.out.println("本帧起始电池序号十六进制数据："+m2Str2);

			int m2 = Integer.parseInt(m2Str,16);             //转换为十进制，单体电池个数
			System.out.println("单体电池个数十进制数据:"+m2);
			if(m2>400){  //分成三包
				if(m2Str2.equals("0191")){//在第三包，长度m2为m2-400
					System.out.println("分成三包，在第三包");
					m2 = m2-400; 
				}else {//在第一包或第二包，长度m2位200
					System.out.println("分成三包，在第二包或第一包");
					m2 = 200;
				}
			}else if(m2 >200){//分成两包
				if(m2Str2.equals("00C9")){//在第三包，长度m2为m2-400
					System.out.println("分成两包包，在第二包");
					m2 = m2-200; 
				}else {//在第一包或第二包，长度m2位200
					System.out.println("分成两包包，在第一包");
					m2 = 200;
				}
			}else{            //分成一包
				System.out.println("只有一包");
			}
			System.out.println("单体电池总数偏移："+m2);
			voltage_his = RemoveBlank.substring(200+m, 224+(4*m2)+m);	//电压历史报文段
			
			String m3Str = RemoveBlank.substring(230+(4*m2)+m, 234+(4*m2)+m);  //温度探针个数的报文段，用于判断温度探针个数，目的是计算偏移以及温度探针历史报文的长度
			int m3 = Integer.parseInt(m3Str,16);             //转换为十进制，单体电池个数
			System.out.println("温度探针偏移："+m3);
			temperature_his = RemoveBlank.substring(224+(4*m2)+m, 234+(4*m2)+(2*m3)+m);	//温度历史报文段
			
			
		}else if(dianjigeshu.equals("01")){//不是停车状态，有一个电机，不做移位
			m = 0;
			vehicle_his = RemoveBlank.substring(60, 102);	   //整车历史报文段
			motor_his = RemoveBlank.substring(102, 130+m);	   //电机历史报文段
			location_his = RemoveBlank.substring(130+m, 150+m);	   //位置历史报文段
			extreme_his = RemoveBlank.substring(150+m, 180+m);	   //极值历史报文段
			alert_his = RemoveBlank.substring(180+m, 200+m);	   //报警历史报文段
			
			
			
			String m2Str = RemoveBlank.substring(214+m, 218+m);  //单体电池总数的报文段，用于判断单体电池个数，目的是计算偏移以及电压历史报文的长度
			String m2Str2 = RemoveBlank.substring(218+m, 222+m);  //本帧起始电池序号，用于判断在第几个包中，便于确定位移
			System.out.println("单体电池个数十六进制数据："+m2Str);
			System.out.println("本帧起始电池序号十六进制数据："+m2Str2);

			int m2 = Integer.parseInt(m2Str,16);             //转换为十进制，单体电池个数
			System.out.println("单体电池个数十进制数据:"+m2);
			if(m2>400){  //分成三包
				if(m2Str2.equals("0191")){//在第三包，长度m2为m2-400
					System.out.println("分成三包，在第三包");
					m2 = m2-400; 
				}else {//在第一包或第二包，长度m2位200
					System.out.println("分成三包，在第二包或第一包");
					m2 = 200;
				}
			}else if(m2 >200){//分成两包
				if(m2Str2.equals("00C9")){//在第三包，长度m2为m2-400
					System.out.println("分成两包包，在第二包");
					m2 = m2-200; 
				}else {//在第一包或第二包，长度m2位200
					System.out.println("分成两包包，在第一包");
					m2 = 200;
				}
			}else{            //分成一包
				System.out.println("只有一包");
			}
			System.out.println("单体电池总数偏移："+m2);
			voltage_his = RemoveBlank.substring(200+m, 224+(4*m2)+m);	//电压历史报文段
			
			String m3Str = RemoveBlank.substring(230+(4*m2)+m, 234+(4*m2)+m);  //温度探针个数的报文段，用于判断温度探针个数，目的是计算偏移以及温度探针历史报文的长度
			int m3 = Integer.parseInt(m3Str,16);             //转换为十进制，单体电池个数
			System.out.println("温度探针偏移："+m3);
			temperature_his = RemoveBlank.substring(224+(4*m2)+m, 234+(4*m2)+(2*m3)+m);	//温度历史报文段
		}else if(dianjigeshu.equals("02")){//不是停车状态，有两个电机，移位24
			m = 24;
			vehicle_his = RemoveBlank.substring(60, 102);	 //整车历史报文段
			motor_his = RemoveBlank.substring(102, 130+m);	   //电机历史报文段
			location_his = RemoveBlank.substring(130+m, 150+m);	   //位置历史报文段
			extreme_his = RemoveBlank.substring(150+m, 180+m);	   //极值历史报文段
			alert_his = RemoveBlank.substring(180+m, 200+m);	   //报警历史报文段
			
			String m2Str = RemoveBlank.substring(214+m, 218+m);  //单体电池总数的报文段，用于判断单体电池个数，目的是计算偏移以及电压历史报文的长度
			String m2Str2 = RemoveBlank.substring(218+m, 222+m);  //本帧起始电池序号，用于判断在第几个包中，便于确定位移
			System.out.println("单体电池个数十六进制数据："+m2Str);
			System.out.println("本帧起始电池序号十六进制数据："+m2Str2);

			int m2 = Integer.parseInt(m2Str,16);             //转换为十进制，单体电池个数
			System.out.println("单体电池个数十进制数据:"+m2);
			if(m2>400){  //分成三包
				if(m2Str2.equals("0191")){//在第三包，长度m2为m2-400
					System.out.println("分成三包，在第三包");
					m2 = m2-400; 
				}else {//在第一包或第二包，长度m2位200
					System.out.println("分成三包，在第二包或第一包");
					m2 = 200;
				}
			}else if(m2 >200){//分成两包
				if(m2Str2.equals("00C9")){//在第三包，长度m2为m2-400
					System.out.println("分成两包包，在第二包");
					m2 = m2-200; 
				}else {//在第一包或第二包，长度m2位200
					System.out.println("分成两包包，在第一包");
					m2 = 200;
				}
			}else{            //分成一包
				System.out.println("只有一包");
			}
			System.out.println("单体电池总数偏移："+m2);
			voltage_his = RemoveBlank.substring(200+m, 224+(4*m2)+m);	//电压历史报文段
			
			String m3Str = RemoveBlank.substring(230+(4*m2)+m, 234+(4*m2)+m);  //温度探针个数的报文段，用于判断温度探针个数，目的是计算偏移以及温度探针历史报文的长度
			int m3 = Integer.parseInt(m3Str,16);             //转换为十进制，单体电池个数
			System.out.println("温度探针偏移："+m3);
			temperature_his = RemoveBlank.substring(224+(4*m2)+m, 234+(4*m2)+(2*m3)+m);	//温度历史报文段
			
		}
		System.out.println("电机存在与否决定的偏移量："+m);
		

		
		System.out.println("====================================↓↓↓↓历史报文Tb_history_message的analysis以下是赋值以后的数==========================================");
		System.out.println("iccid："+iccid);
		System.out.println("报文时间message_time："+message_time);
		System.out.println("报文长度message_length："+message_length);
		System.out.println("原始报文original_message："+original_message);
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
	
	
	
	
	
	

	
}
