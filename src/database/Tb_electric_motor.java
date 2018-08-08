package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Tb_electric_motorBean;

/**
 * 电机数据表Tb_electric_motor的操作
 * @author zuck zhao
 *
 */
public class Tb_electric_motor {
	

	/**
	 * 电机数据相关变量
	 */
	String vin = null;   //车架号
	//String message_time = null;		//报文时间
	String validity = "1";				//数据有效性(暂时置为1)
	String amount = null;				//驱动电机个数
	String order_1_1 = null;			//电机序号1
	String status_1_2 = null;			//电机状态1
	String controller_temp_1_3 = null;	//电机控制器温度1
	String rotate_speed_1_4 = null;		//电机转速1
	String torque_1_5 = null;			//电机转矩1
	String temperature_1_6 = null;		//电机温度1
	String voltage_1_7 = null;			//电机电压1
	String electricity_1_8 = null;		//电机母线电流1
	String order_2_1 = null;			//电机序号2
	String status_2_2 = null;			//电机状态2
	String controller_temp_2_3 = null;	//电机控制器温度2
	String rotate_speed_2_4 = null;		//电机转速2
	String torque_2_5 = null;			//电机转矩2
	String temperature_2_6 = null;		//电机温度2
	String voltage_2_7 = null;			//电机电压2
	String electricity_2_8 = null;		//电机母线电流2
	
	//String message_time = "time";		//报文时间
/*	String validity = "validaty";		//数据有效性
	String amount = "12345678910111213141516171819";//驱动电机个数
	String order_1_1 = "11";			//电机序号1
	String status_1_2 = "12";			//电机状态1
	String controller_temp_1_3 = "13";	//电机控制器温度1
	String rotate_speed_1_4 = "14";		//电机转速1
	String torque_1_5 = "15";			//电机转矩1
	String temperature_1_6 = "16";		//电机温度1
	String voltage_1_7 = "17";			//电机电压1
	String electricity_1_8 = "18";		//电机母线电流1
	String order_2_1 = "21";			//电机序号2
	String status_2_2 = "22";			//电机状态2
	String controller_temp_2_3 = "23";	//电机控制器温度2
	String rotate_speed_2_4 = "24";		//电机转速2
	String torque_2_5 = "25";			//电机转矩2
	String temperature_2_6 = "26";		//电机温度2
	String voltage_2_7 = "27";			//电机电压2
	String electricity_2_8 = "28";		//电机母线电流2
*/
	
	
	/**
	 * 插入解析后的数据
	 */
	public void insert(){
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql =  "insert into tb_electric_motor(vin,validity,amount,order_1_1,status_1_2,controller_temp_1_3,"
				+ "rotate_speed_1_4,torque_1_5,temperature_1_6,voltage_1_7,electricity_1_8,order_2_1,status_2_2,controller_temp_2_3,rotate_speed_2_4,"
				+ "torque_2_5,temperature_2_6,voltage_2_7,electricity_2_8) "
				+ "values('"+vin+"','"+validity+"','"+amount+"','"+order_1_1+"','"+status_1_2+"','"+controller_temp_1_3+"','"+rotate_speed_1_4+"','"+torque_1_5+"',  "
				+ "  '"+temperature_1_6+"','"+voltage_1_7+"','"+electricity_1_8+"','"+order_2_1+"','"+status_2_2+"','"+controller_temp_2_3+"','"+rotate_speed_2_4+"',  "
				+ "'"+torque_2_5+"','"+temperature_2_6+"','"+voltage_2_7+"','"+electricity_2_8+"')";
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
			System.out.println("电机数据表Tb_electric_motor的insert执行完毕，关闭所有连接，插入成功");
		}

	}
	
	/**
	 * 查询数据
	 */
	public Tb_electric_motorBean select(String vin){
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from tb_electric_motor where vin ='"+vin+"' order by id desc LIMIT 1";
		Tb_electric_motorBean Bean = new Tb_electric_motorBean();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);   //插入不能使用executeQuery()
			while(rs.next()){
				Bean.setMessage_time(rs.getString("message_time"));  
				Bean.setValidity(rs.getString("validity"));
				Bean.setAmount(rs.getString("amount"));
				Bean.setOrder_1_1(rs.getString("order_1_1"));
				Bean.setStatus_1_2(rs.getString("status_1_2"));
				Bean.setController_temp_1_3(rs.getString("controller_temp_1_3"));
				Bean.setRotate_speed_1_4(rs.getString("rotate_speed_1_4"));
				Bean.setTorque_1_5(rs.getString("torque_1_5"));
				Bean.setTemperature_1_6(rs.getString("temperature_1_6"));
				Bean.setVoltage_1_7(rs.getString("voltage_1_7"));
				Bean.setElectricity_1_8(rs.getString("electricity_1_8"));
				Bean.setOrder_2_1(rs.getString("order_2_1"));
				Bean.setStatus_2_2(rs.getString("status_2_2"));
				Bean.setController_temp_2_3(rs.getString("controller_temp_2_3"));
				Bean.setRotate_speed_2_4(rs.getString("rotate_speed_2_4"));
				Bean.setTorque_2_5(rs.getString("torque_2_5"));
				Bean.setTemperature_2_6(rs.getString("temperature_2_6"));
				Bean.setVoltage_2_7(rs.getString("voltage_2_7"));
				Bean.setElectricity_2_8(rs.getString("electricity_2_8"));
				System.out.println("Tb_electric_motor的select获取数据");
			}
			
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
			System.out.println("电机数据表Tb_electric_motor的select执行完毕，关闭所有连接");
		}
		return Bean;
	}
	
	/**
	 * 从数据包找出相应的数据，并且赋值给相应的变量
	 */
	public void analysis(String currentTime){
		
		String str = currentTime.replace(" ", "");//去除空格
		
		/**
		 * 无论何种情况，车架号始终是有值的
		 */
		String Stringvin = str.substring(8, 42);//vin赋值
		char[] charvin = change(Stringvin); //对vin进行转换
		for(int i=0 ;i<17;i++){
			if(i == 0 ){
				vin = String.valueOf(charvin[0]);
			}else{
				vin = vin+charvin[i];
			}
			
		}
		String chongdianzhuangtai = str.substring(64,66);	//充电状态，用于判断移位，停车充电时，没有电机数据，所有值都置“-”
		if(chongdianzhuangtai.equals("01")){//01表示停车充电，没有任何电机数据，执行完if便结束
			System.out.println("*************************Tb_electric_motor的analysis判断为：停车充电，所以没有电机数据，所有值置--*********************************************");
			/**
			 * 电机数据相关变量
			 */
			//vin = vin;   //车架号
			//String message_time = null;		//报文时间
			validity = "1";				//数据有效性(暂时置为1)
			amount = "0";				//驱动电机个数设置为0
			order_1_1 = "--";			//电机序号1
			status_1_2 = "--";			//电机状态1
			controller_temp_1_3 = "--";	//电机控制器温度1
			rotate_speed_1_4 = "--";		//电机转速1
			torque_1_5 = "--";			//电机转矩1
			temperature_1_6 = "--";		//电机温度1
			voltage_1_7 = "--";			//电机电压1
			electricity_1_8 = "--";		//电机母线电流1
			order_2_1 = "--";			//电机序号2
			status_2_2 = "--";			//电机状态2
			controller_temp_2_3 = "--";	//电机控制器温度2
			rotate_speed_2_4 = "--";		//电机转速2
			torque_2_5 = "--";			//电机转矩2
			temperature_2_6 = "--";		//电机温度2
			voltage_2_7 = "--";			//电机电压2
			electricity_2_8 = "--";		//电机母线电流2
			return ;
		}
		
		
		
		
		/**
		 * 以下为有电机数据的情况
		 */
		System.out.println("Tb_electric_motor的analysis判断为：不是停车充电，有电机数据");
		
		

		String amountStr = str.substring(104, 106);
		int amountInt = Integer.valueOf(amountStr,16);
		amount = Integer.toString(amountInt); //给电机个数赋值
		
		if(amountInt == 1){//根据驱动电机个数来给各项驱动数据赋值
			String orderStr_1_1 = str.substring(106,108);			//电机序号1
			String statusStr_1_2 = str.substring(108,110);			//电机状态1
			String controller_tempStr_1_3 = str.substring(110,112);//电机控制器温度1
			String rotate_speedStr_1_4 = str.substring(112,116);	//电机转速1
			String torqueStr_1_5 = str.substring(116,120);			//电机转矩1
			String temperatureStr_1_6 = str.substring(120,122);	//电机温度1
			String voltageStr_1_7 = str.substring(122,126);		//电机电压1
			String electricityStr_1_8 = str.substring(126,130);	//电机母线电流1
			
			System.out.println("--------------直接从字符串中获取的十六进制字符值----------");
			System.out.println("orderStr_1_1电机序号1:"+orderStr_1_1);
			System.out.println("statusStr_1_2电机状态1:"+statusStr_1_2);
			System.out.println("controller_tempStr_1_3电机控制器温度1:"+controller_tempStr_1_3);
			System.out.println("rotate_speedStr_1_4电机转速1:"+rotate_speedStr_1_4);
			System.out.println("torqueStr_1_5电机转矩1:"+torqueStr_1_5);
			System.out.println("temperatureStr_1_6电机温度1:"+temperatureStr_1_6);
			System.out.println("voltageStr_1_7电机电压1:"+voltageStr_1_7);
			System.out.println("electricityStr_1_8电机母线电流1:"+electricityStr_1_8);
			
			/**
			 * 以下语句执行赋值操作
			 */
			int orderStr_1_1Int = Integer.valueOf(orderStr_1_1,16); 
			if(orderStr_1_1Int>=0 && orderStr_1_1Int<=253){//判断电机序号1，并赋值
				order_1_1 = Integer.toString(orderStr_1_1Int);
			}else{
				System.out.println("order_1_1电机序号1范围不正常，值为："+order_1_1);
			}
			
			if( statusStr_1_2.equals("01")){//电机状态1判断和赋值
				status_1_2 = "耗电";
			}else if(statusStr_1_2.equals("02")){
				status_1_2 = "发电";
			}else if(statusStr_1_2.equals("03")){
				status_1_2 = "关闭状态";	
			}else if(statusStr_1_2.equals("04")){
				status_1_2 = "准备状态";	
			}else if(statusStr_1_2.equals("FE")){
				status_1_2 = "异常";	
			}else if(statusStr_1_2.equals("FF")){
				status_1_2 = "无效";				
			}else{
				status_1_2 = statusStr_1_2+"（错）";
				System.out.println("status_1_2电机状态1字符不正确");
			}
			
			if(controller_tempStr_1_3.equals("FE")){//电机控制器温度1判断和赋值
				controller_temp_1_3 = "异常";
			}else if(controller_tempStr_1_3.equals("FF")){
				controller_temp_1_3 = "无效";			
			}else{
				int a = Integer.valueOf(controller_tempStr_1_3,16);//获取十进制数值
				if(a>=0&&a<=250){
					double controller_tempDou_1_3 = a -40;  //设置偏移量-40
					controller_temp_1_3 = controller_tempDou_1_3+"";// 去掉℃
				}else{
					controller_temp_1_3 = controller_tempStr_1_3+"（错）";
					System.out.println("取得值为："+a+",controller_tempStr_1_3电机控制器温度1不在有效范围0-250");
				}
			}
			
			if(rotate_speedStr_1_4.equals("FFFE")){//电机转速1判断和赋值
				rotate_speed_1_4 = "异常";
			}else if(rotate_speedStr_1_4.equals("FFFF")){
				rotate_speed_1_4 = "无效";			
			}else{
				int a = Integer.valueOf(rotate_speedStr_1_4,16);//获取十进制数值
				System.out.println("电机转速转换为十进制的值："+a);
				if(a>=0 && a<=65531){
					double rotate_speedStrDou_1_4 = a -20000;  //设置偏移量-20000
					rotate_speed_1_4 = rotate_speedStrDou_1_4+"";// 去掉单位r/min
				}else{
					rotate_speed_1_4 = rotate_speedStr_1_4+"（错）";
					System.out.println("取得值为："+a+",rotate_speed_1_4电机转速1不在有效范围0-65531");
				}
			}
			
			if(torqueStr_1_5.equals("FFFE")){//电机转矩1判断和赋值
				torque_1_5 = "异常";
			}else if(torqueStr_1_5.equals("FFFF")){
				torque_1_5 = "无效";			
			}else{
				int a = Integer.valueOf(torqueStr_1_5,16);//获取十进制数值
				if(a>=0 && a<=65531){
					double torqueStrDou_1_5 = a/10 -2000;  //设置偏移量
					torque_1_5 = torqueStrDou_1_5+""; // 去掉单位N*m
				}else{
					torque_1_5 = torqueStr_1_5+"（错）";
					System.out.println("取得值为："+a+",torque_1_5电机转矩1不在有效范围0-65531");
				}
			}
			
			if(temperatureStr_1_6.equals("FE")){//电机温度1判断和赋值
				temperature_1_6 = "异常";
			}else if(temperatureStr_1_6.equals("FF")){
				temperature_1_6 = "无效";			
			}else{
				int a = Integer.valueOf(temperatureStr_1_6,16);//获取十进制数值
				if(a>=0 && a<=250){
					double temperatureStrDou_1_6 = a - 40;  //设置偏移量-40
					temperature_1_6 = temperatureStrDou_1_6+""; //去掉 ℃
				}else{
					temperature_1_6 = temperatureStr_1_6+"（错）";
					System.out.println("取得值为："+a+",temperature_1_6电机温度1不在有效范围0-250");
				}
			}
			
			if(voltageStr_1_7.equals("FFFE")){//电机电压1判断和赋值
				voltage_1_7 = "异常";
			}else if(voltageStr_1_7.equals("FFFF")){
				voltage_1_7 = "无效";			
			}else{
				int a = Integer.valueOf(voltageStr_1_7,16);//获取十进制数值
				if(a>=0 && a<=60000){
					double voltageStrDou_1_7 = a/10 ;  //设置偏移
					voltage_1_7 = voltageStrDou_1_7+"";   // 去掉单位V
				}else{
					voltage_1_7 = voltageStr_1_7+"（错）";
					System.out.println("取得值为："+a+",voltage_1_7电机电压1不在有效范围0-60000");
				}
			}
			
			if(electricityStr_1_8.equals("FFFE")){//电机母线电流1判断和赋值
				electricity_1_8 = "异常";
			}else if(electricityStr_1_8.equals("FFFF")){
				electricity_1_8 = "无效";			
			}else{
				int a = Integer.valueOf(electricityStr_1_8,16);//获取十进制数值
				if(a>=0 && a<=20000){
					double electricityStrDou_1_8 = a/10 - 1000 ;  //设置偏移-1000
					electricity_1_8 = electricityStrDou_1_8+"";//去掉单位 A
				}else{
					electricity_1_8 = electricityStr_1_8+"（错）" ;
					System.out.println("取得值为："+a+",electricity_1_8电机母线电流1不在有效范围0-20000");
				}
			}
			
			/**
			 * 当只有一个电机时候将第二个电机的值赋为"--"
			 */
			order_2_1 = "--";			//电机序号2      
		    status_2_2 = "--";			//电机状态2
			controller_temp_2_3 = "--";	//电机控制器温度2
			rotate_speed_2_4 = "--";		//电机转速2
			torque_2_5 = "--";			//电机转矩2
			temperature_2_6 = "--";		//电机温度2
			voltage_2_7 = "--";			//电机电压2
			electricity_2_8 = "--";		//电机母线电流2
			//以上为一个电机
			
		}else{
			
			String orderStr_1_1 = str.substring(106,108);			//电机序号1
			String statusStr_1_2 = str.substring(108,110);			//电机状态1
			String controller_tempStr_1_3 = str.substring(110,112);//电机控制器温度1
			String rotate_speedStr_1_4 = str.substring(112,116);	//电机转速1
			String torqueStr_1_5 = str.substring(116,120);			//电机转矩1
			String temperatureStr_1_6 = str.substring(120,122);	//电机温度1
			String voltageStr_1_7 = str.substring(122,126);		//电机电压1
			String electricityStr_1_8 = str.substring(126,130);	//电机母线电流1
			String orderStr_2_1 = str.substring(130,132);			//电机序号2
			String statusStr_2_2 = str.substring(132,134);;		//电机状态2
			String controller_tempStr_2_3 = str.substring(134,136);//电机控制器温度2
			String rotate_speedStr_2_4 = str.substring(136,140);	//电机转速2
			String torqueStr_2_5 = str.substring(140,144);			//电机转矩2
			String temperatureStr_2_6 = str.substring(144,146);	//电机温度2
			String voltageStr_2_7 = str.substring(146,150);		//电机电压2
			String electricityStr_2_8 = str.substring(150,154);	//电机母线电流2
			
			System.out.println("--------------直接从字符串中获取的十六进制字符值----------");
			System.out.println("orderStr_1_1电机序号1:"+orderStr_1_1);
			System.out.println("statusStr_1_2电机状态1:"+statusStr_1_2);
			System.out.println("controller_tempStr_1_3电机控制器温度1:"+controller_tempStr_1_3);
			System.out.println("rotate_speedStr_1_4电机转速1:"+rotate_speedStr_1_4);
			System.out.println("torqueStr_1_5电机转矩1:"+torqueStr_1_5);
			System.out.println("temperatureStr_1_6电机温度1:"+temperatureStr_1_6);
			System.out.println("voltageStr_1_7电机电压1:"+voltageStr_1_7);
			System.out.println("electricityStr_1_8电机母线电流1:"+electricityStr_1_8);
			System.out.println("orderStr_2_1电机序号2:"+orderStr_2_1);
			System.out.println("statusStr_2_2电机状态2:"+statusStr_2_2);
			System.out.println("controller_tempStr_2_3电机控制器温度2:"+controller_tempStr_2_3);
			System.out.println("rotate_speedStr_2_4电机转速2:"+rotate_speedStr_2_4);
			System.out.println("torqueStr_2_5电机转矩2:"+torqueStr_2_5);
			System.out.println("temperatureStr_2_6电机温度2:"+temperatureStr_2_6);
			System.out.println("voltageStr_2_7电机电压2:"+voltageStr_2_7);
			System.out.println("electricityStr_2_8电机母线电流2:"+electricityStr_2_8);
		
			/**
			 * 第一个电机赋值
			 */
			int orderStr_1_1Int = Integer.valueOf(orderStr_1_1,16); 
			if(orderStr_1_1Int>=0 && orderStr_1_1Int<=253){//判断电机序号1，并赋值
				order_1_1 = Integer.toString(orderStr_1_1Int);
			}else{
				System.out.println("order_1_1电机序号1范围不正常，值为："+order_1_1);
			}
			
			if( statusStr_1_2.equals("01")){//电机状态1判断和赋值
				status_1_2 = "耗电";
			}else if(statusStr_1_2.equals("02")){
				status_1_2 = "发电";
			}else if(statusStr_1_2.equals("03")){
				status_1_2 = "关闭状态";	
			}else if(statusStr_1_2.equals("04")){
				status_1_2 = "准备状态";	
			}else if(statusStr_1_2.equals("FE")){
				status_1_2 = "异常";	
			}else if(statusStr_1_2.equals("FF")){
				status_1_2 = "无效";				
			}else{
				status_1_2 = statusStr_1_2+"（错）";
				System.out.println("status_1_2电机状态1字符不正确");
			}
			
			if(controller_tempStr_1_3.equals("FE")){//电机控制器温度1判断和赋值
				controller_temp_1_3 = "异常";
			}else if(controller_tempStr_1_3.equals("FF")){
				controller_temp_1_3 = "无效";
			}else{
				int a = Integer.valueOf(controller_tempStr_1_3,16);//获取十进制数值
				if(a>=0&&a<=250){
					double controller_tempDou_1_3 = a -40;  //设置偏移量-40
					controller_temp_1_3 = controller_tempDou_1_3+""; //去掉单位℃
				}else{
					controller_temp_1_3 = controller_tempStr_1_3+"（错）";
					System.out.println("取得值为："+a+",controller_tempStr_1_3电机控制器温度1不在有效范围0-250");
				}
			}
			
			if(rotate_speedStr_1_4.equals("FFFE")){//电机转速1判断和赋值
				rotate_speed_1_4 = "异常";
			}else if(rotate_speedStr_1_4.equals("FFFF")){
				rotate_speed_1_4 = "无效";			
			}else{
				int a = Integer.valueOf(rotate_speedStr_1_4,16);//获取十进制数值
				if(a>=0 && a<=65531){
					double rotate_speedStrDou_1_4 = a -20000;  //设置偏移量-20000
					rotate_speed_1_4 = rotate_speedStrDou_1_4+"";// 去掉单位r/min
				}else{
					rotate_speed_1_4 = rotate_speedStr_1_4+"（错）";
					System.out.println("取得值为："+a+",rotate_speed_1_4电机转速1不在有效范围0-65531");
				}
			}
			
			if(torqueStr_1_5.equals("FFFE")){//电机转矩1判断和赋值
				torque_1_5 = "异常";
			}else if(torqueStr_1_5.equals("FFFF")){
				torque_1_5 = "无效";			
			}else{
				int a = Integer.valueOf(torqueStr_1_5,16);//获取十进制数值
				if(a>=0 && a<=65531){
					double torqueStrDou_1_5 = a/10 -2000;  //设置偏移量
					torque_1_5 = torqueStrDou_1_5+"";//去掉单位 N*m
				}else{
					torque_1_5 = torqueStr_1_5+"（错）";
					System.out.println("取得值为："+a+",torque_1_5电机转矩1不在有效范围0-65531");
				}
			}
			
			if(temperatureStr_1_6.equals("FE")){//电机温度1判断和赋值
				temperature_1_6 = "异常";
			}else if(temperatureStr_1_6.equals("FF")){
				temperature_1_6 = "无效";			
			}else{
				int a = Integer.valueOf(temperatureStr_1_6,16);//获取十进制数值
				if(a>=0 && a<=250){
					double temperatureStrDou_1_6 = a - 40;  //设置偏移量-40
					temperature_1_6 = temperatureStrDou_1_6+"";//去掉单位 ℃
				}else{
					temperature_1_6 = temperatureStr_1_6+"（错）";
					System.out.println("取得值为："+a+",temperature_1_6电机温度1不在有效范围0-250");
				}
			}
			
			if(voltageStr_1_7.equals("FFFE")){//电机电压1判断和赋值
				voltage_1_7 = "异常";
			}else if(voltageStr_1_7.equals("FFFF")){
				voltage_1_7 = "无效";			
			}else{
				int a = Integer.valueOf(voltageStr_1_7,16);//获取十进制数值
				if(a>=0 && a<=60000){
					double voltageStrDou_1_7 = a/10 ;  //设置偏移
					voltage_1_7 = voltageStrDou_1_7+"";//去掉单位 V
				}else{
					voltage_1_7 = voltageStr_1_7+"（错）";
					System.out.println("取得值为："+a+",voltage_1_7电机电压1不在有效范围0-60000");
				}
			}
			
			if(electricityStr_1_8.equals("FFFE")){//电机母线电流1判断和赋值
				electricity_1_8 = "异常";
			}else if(electricityStr_1_8.equals("FFFF")){
				electricity_1_8 = "无效";			
			}else{
				int a = Integer.valueOf(electricityStr_1_8,16);//获取十进制数值
				if(a>=0 && a<=20000){
					double electricityStrDou_1_8 = a/10 - 1000 ;  //设置偏移-1000
					electricity_1_8 = electricityStrDou_1_8+"";//去掉单位 A
				}else{
					electricity_1_8 = electricityStr_1_8+"（错）";
					System.out.println("取得值为："+a+",electricity_1_8电机母线电流1不在有效范围0-20000");
				}
			}
			
			/**
			 * 第二个电机赋值
			 */
			int orderStr_2_1Int = Integer.valueOf(orderStr_2_1,16); 
			if(orderStr_2_1Int>=0 && orderStr_2_1Int<=253){//判断电机序号2，并赋值
				order_2_1 = Integer.toString(orderStr_2_1Int);
			}else{
				order_2_1 = orderStr_2_1Int+"（错）";
				System.out.println("order_2_1电机序号2范围不正常，值为："+order_2_1);
			}
			
			if( statusStr_2_2.equals("01")){//电机状态2判断和赋值
				status_2_2 = "耗电";
			}else if(statusStr_2_2.equals("02")){
				status_2_2 = "发电";
			}else if(statusStr_2_2.equals("03")){
				status_2_2 = "关闭状态";	
			}else if(statusStr_2_2.equals("04")){
				status_2_2 = "准备状态";	
			}else if(statusStr_2_2.equals("FE")){
				status_2_2 = "异常";	
			}else if(statusStr_2_2.equals("FF")){
				status_2_2 = "无效";
			}else{
				status_2_2 = statusStr_2_2+"（错）";
				System.out.println("status_2_2电机状态2字符不正确");
			}
			
			if(controller_tempStr_2_3.equals("FE")){//电机控制器温度2判断和赋值
				controller_temp_2_3 = "异常";
			}else if(controller_tempStr_2_3.equals("FF")){
				controller_temp_2_3 = "无效";			
			}else{
				int a = Integer.valueOf(controller_tempStr_2_3,16);//获取十进制数值
				if(a>=0&&a<=250){
					double controller_tempDou_2_3 = a -40;  //设置偏移量-40
					controller_temp_2_3 = controller_tempDou_2_3+"";//去掉单位 ℃
				}else{
					controller_temp_2_3 = controller_tempStr_2_3+"（错）";
					System.out.println("取得值为："+a+",controller_tempStr_2_3电机控制器温度2不在有效范围0-250");
				}
			}
			
			if(rotate_speedStr_2_4.equals("FFFE")){//电机转速2判断和赋值
				rotate_speed_2_4 = "异常";
			}else if(rotate_speedStr_2_4.equals("FFFF")){
				rotate_speed_2_4 = "无效";		
			}else{
				int a = Integer.valueOf(rotate_speedStr_2_4,16);//获取十进制数值
				if(a>=0 && a<=65531){
					double rotate_speedStrDou_2_4 = a -20000;  //设置偏移量-20000
					rotate_speed_2_4 = rotate_speedStrDou_2_4+"";//去掉单位 r/min
				}else{
					rotate_speed_2_4 = rotate_speedStr_2_4+"（错）";
					System.out.println("取得值为："+a+",rotate_speed_2_4电机转速2不在有效范围0-65531");
				}
			}
			
			if(torqueStr_2_5.equals("FFFE")){//电机转矩2判断和赋值
				torque_2_5 = "异常";
			}else if(torqueStr_2_5.equals("FFFF")){
				torque_2_5 = "无效";			
			}else{
				int a = Integer.valueOf(torqueStr_2_5,16);//获取十进制数值
				if(a>=0 && a<=65531){
					double torqueStrDou_2_5 = a/10 -2000;  //设置偏移量
					torque_2_5 = torqueStrDou_2_5+"";//去掉单位 N*m
				}else{
					torque_2_5 = torqueStr_2_5+"（错）";
					System.out.println("取得值为："+a+",torque_2_5电机转矩2不在有效范围0-65531");
				}
			}
			
			if(temperatureStr_2_6.equals("FE")){//电机温度2判断和赋值
				temperature_2_6 = "异常";
			}else if(temperatureStr_2_6.equals("FF")){
				temperature_2_6 = "无效";			
			}else{
				int a = Integer.valueOf(temperatureStr_2_6,16);//获取十进制数值
				if(a>=0 && a<=250){
					double temperatureStrDou_2_6 = a - 40;  //设置偏移量-40
					temperature_2_6 = temperatureStrDou_2_6+"";//去掉单位 ℃
				}else{
					temperature_2_6 = temperatureStr_2_6+"（错）";
					System.out.println("取得值为："+a+",temperature_2_6电机温度2不在有效范围0-250");
				}
			}
			
			if(voltageStr_2_7.equals("FFFE")){//电机电压2判断和赋值
				voltage_2_7 = "异常";
			}else if(voltageStr_2_7.equals("FFFF")){
				voltage_2_7 = "无效";			
			}else{
				int a = Integer.valueOf(voltageStr_2_7,16);//获取十进制数值
				if(a>=0 && a<=60000){
					double voltageStrDou_2_7 = a/10 ;  //设置偏移
					voltage_2_7 = voltageStrDou_2_7+"";//去掉单位 V
				}else{
					voltage_2_7 = voltageStr_2_7+"（错）";
					System.out.println("取得值为："+a+",voltage_2_7电机电压2不在有效范围0-60000");
				}
			}
			
			if(electricityStr_2_8.equals("FFFE")){//电机母线电流2判断和赋值
				electricity_2_8 = "异常";//异常情况
			}else if(electricityStr_2_8.equals("FFFF")){//
				electricity_2_8 = "无效";//			
			}else{
				int a = Integer.valueOf(electricityStr_2_8,16);//获取十进制数值
				if(a>=0 && a<=20000){
					double electricityStrDou_2_8 = a/10 - 1000 ;  //设置偏移-1000
					electricity_2_8 = electricityStrDou_2_8+"";//去掉单位 A
				}else{
					electricity_2_8 = electricityStr_2_8+"（错）";
					System.out.println("取得值为："+a+",electricity_2_8电机母线电流2不在有效范围0-20000");
				}
			}
	
		}//最外围的一个else
		
		System.out.println("========================================↓↓↓↓以下是驱动电机数据Tb_electric_motor的analysis执行后赋值=====================================");
		System.out.println("orderStr_1_1电机序号1:"+order_1_1);
		System.out.println("statusStr_1_2电机状态1:"+status_1_2);
		System.out.println("controller_tempStr_1_3电机控制器温度1:"+controller_temp_1_3);
		System.out.println("rotate_speedStr_1_4电机转速1:"+rotate_speed_1_4);
		System.out.println("torqueStr_1_5电机转矩1:"+torque_1_5);
		System.out.println("temperatureStr_1_6电机温度1:"+temperature_1_6);
		System.out.println("voltageStr_1_7电机电压1:"+voltage_1_7);
		System.out.println("electricityStr_1_8电机母线电流1:"+electricity_1_8);
		System.out.println("orderStr_2_1电机序号2:"+order_2_1);
		System.out.println("statusStr_2_2电机状态2:"+status_2_2);
		System.out.println("controller_tempStr_2_3电机控制器温度2:"+controller_temp_2_3);
		System.out.println("rotate_speedStr_2_4电机转速2:"+rotate_speed_2_4);
		System.out.println("torqueStr_2_5电机转矩2:"+torque_2_5);
		System.out.println("temperatureStr_2_6电机温度2:"+temperature_2_6);
		System.out.println("voltageStr_2_7电机电压2:"+voltage_2_7);
		System.out.println("electricityStr_2_8电机母线电流2:"+electricity_2_8);
		System.out.println("========================================↑↑↑↑是驱动电机数据Tb_electric_motor的analysis执行后赋值========================================");
	}//方法体
	
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





