package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Tb_vehicleBean;

/**
 * 整车数据表tb_vehicle的操作
 * @author zuck zhao
 *
 */
public class Tb_vehicle {
	

	/**
	 * 电机数据相关变量
	 */
	String vin = null;                      //车架号
	String ve_status1 = null;				//车辆状态
	String charge_status2 = null;			//充电状态
	String operation_pattern3 = null;		//运行模式
	String ve_speed4 = null;				//车速
	String mileage5 = null;					//里程
	String total_voltage6 = null;			//总电压
	String total_electricity7 = null;		//总电流
	String soc8 = null;						//SOC
	String dcdc_status9 = null;				//DC-DC状态
	String grade10 = null;					//档位
	String resistance11 = null;				//正极对地电阻
	String accelerate_length12 = null;		//加速踏板行程值
	String brake_length13 = null;			//制动踏板行程值
	String drive_status14 = null;			//驱动状态
	String brake_status15 = null;			//制动状态

	
	
//	String ve_status1 = "ve_status1";					//车辆状态
//	String charge_status2 = "charge_status2";			//充电状态
//	String operation_pattern3 = "operation_pattern3";	//运行模式
//	String ve_speed4 = "ve_speed4";						//车速
//	String mileage5 = "mileage5";						//里程
//	String total_voltage6 = "total_voltage6";			//总电压
//	String total_electricity7 = "total_electricity7";	//总电流
//	String soc8 = "soc8";								//SOC
//	String dcdc_status9 = "dcdc_status9";				//DC-DC状态
//	String grade10 = "grade10";							//档位
//	String resistance11 = "resistance11";				//正极对地电阻
//	String accelerate_length12 = "accelerate_length12";	//加速踏板行程值
//	String brake_length13 = "brake_length13";			//制动踏板行程值
//	String drive_status14 = "drive_status14";			//驱动状态
//	String brake_status15 = "brake_status15";			//制动状态


	
	
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
		
		String sql =  "insert into tb_vehicle(vin,ve_status1,charge_status2,operation_pattern3,ve_speed4,mileage5,"
						+ "total_voltage6,total_electricity7,soc8,dcdc_status9,grade10,resistance11,accelerate_length12,brake_length13,"
						+ "drive_status14,brake_status15) "
						+ "values('"+vin+"','"+ve_status1+"','"+charge_status2+"','"+operation_pattern3+"','"+ve_speed4+"','"+mileage5+"','"+total_voltage6+"','"+total_electricity7+"',  "
						+ "  '"+soc8+"','"+dcdc_status9+"','"+grade10+"','"+resistance11+"','"+accelerate_length12+"','"+brake_length13+"',  "
						+ "'"+drive_status14+"','"+brake_status15+"')";
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
			System.out.println("整车数据表tb_vehicle的insert执行完毕，关闭所有连接，插入成功");
		}

	}
	
	/**
	 * 查询数据
	 */
	public Tb_vehicleBean select(String vin){
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		Tb_vehicleBean TbvBean = new Tb_vehicleBean();
		String sql = "select * from tb_vehicle where vin ='"+vin+"' order by id desc LIMIT 1";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);   //插入不能使用executeQuery()
			while(rs.next()){
				TbvBean.setVe_status1(rs.getString("ve_status1"));
				TbvBean.setCharge_status2(rs.getString("charge_status2"));
				TbvBean.setOperation_pattern3(rs.getString("operation_pattern3"));
				TbvBean.setVe_speed4(rs.getString("ve_speed4"));
				TbvBean.setMileage5(rs.getString("mileage5"));
				TbvBean.setTotal_voltage6(rs.getString("total_voltage6"));
				TbvBean.setTotal_electricity7(rs.getString("total_electricity7"));
				TbvBean.setSoc8(rs.getString("soc8"));
				TbvBean.setDcdc_status9(rs.getString("dcdc_status9"));
				TbvBean.setGrade10(rs.getString("grade10"));
				TbvBean.setResistance11(rs.getString("resistance11"));
				TbvBean.setAccelerate_length12(rs.getString("accelerate_length12"));
				TbvBean.setBrake_length13(rs.getString("brake_length13"));
				TbvBean.setDrive_status14(rs.getString("drive_status14"));
				TbvBean.setBrake_status15(rs.getString("brake_status15"));
				System.out.println("Tb_vehicleBean的select执行完毕");
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
			System.out.println("整车数据表tb_vehicle的select执行完毕，关闭所有连接");
		}
		return TbvBean;
	}
	
	/**
	 * 从数据包找出相应的数据，并且赋值给相应的变量
	 */
	public void analysis(String str){
		String RemoveBlank = str.replace(" ", "");//去除空格
		
		String Stringvin = str.substring(8, 42);//vin赋值
		char[] charvin = change(Stringvin); //对vin进行转换
		for(int i=0 ;i<17;i++){
			if(i == 0 ){
				vin = String.valueOf(charvin[0]);
			}else{
				vin = vin+charvin[i];
			}
			
		}
		/**
		 * 获取十六进制字符
		 */
		String ve_statusStr1 = RemoveBlank.substring(62, 64);			//车辆状态
		String charge_statusStr2 = RemoveBlank.substring(64, 66);		//充电状态
		String operation_patternStr3 = RemoveBlank.substring(66, 68);	//运行模式
		String ve_speedStr4 = RemoveBlank.substring(68, 72);			//车速
		String mileageStr5 = RemoveBlank.substring(72, 80);				//里程
		String total_voltageStr6 = RemoveBlank.substring(80, 84);		//总电压
		String total_electricityStr7 = RemoveBlank.substring(84, 88);	//总电流
		String socStr8 = RemoveBlank.substring(88, 90);					//SOC
		String dcdc_statusStr9 = RemoveBlank.substring(90, 92);			//DC-DC状态
		String gradeStr10 = RemoveBlank.substring(93, 94);				//档位
		String resistanceStr11 = RemoveBlank.substring(94, 98);			//正极对地电阻
		String accelerate_lengthStr12 = RemoveBlank.substring(98, 100);	//加速踏板行程值
		String brake_lengthStr13 = RemoveBlank.substring(100, 102);		//制动踏板行程值
		String drive_statusStr14 = RemoveBlank.substring(92, 93);		//驱动状态
		String brake_statusStr15 = RemoveBlank.substring(92, 93);		//制动状态
		
		if( ve_statusStr1.equals("01")){//车辆状态判断和赋值
			ve_status1 = "汽车启动状态";
		}else if(ve_statusStr1.equals("02")){
			ve_status1 = "熄火";
		}else if(ve_statusStr1.equals("03")){
			ve_status1 = "其他状态";	
		}else if(ve_statusStr1.equals("FE")){
			ve_status1 = "异常";	
		}else if(ve_statusStr1.equals("FF")){
			ve_status1 = "无效";				
		}else{
			ve_status1 = ve_statusStr1+"（错）";
			System.out.println("ve_statusStr1车辆状态字符不正确");
		}
		
		if(charge_statusStr2.equals("01")){//充电状态判断和赋值
			charge_status2 = "停车充电";
		}else if(charge_statusStr2.equals("02")){
			charge_status2 = "行驶充电";
		}else if(charge_statusStr2.equals("03")){
			charge_status2 = "未充电状态";	
		}else if(charge_statusStr2.equals("04")){
			charge_status2 = "充电完成";	
		}else if(charge_statusStr2.equals("FE")){
			charge_status2 = "异常";	
		}else if(charge_statusStr2.equals("FF")){
			charge_status2 = "无效";				
		}else{
			charge_status2 = charge_statusStr2+"（错）";
			System.out.println("charge_status2充电状态字符不正确");
		}
		
		if(operation_patternStr3.equals("01")){//运行模式判断和赋值
			operation_pattern3 = "纯电";
		}else if(operation_patternStr3.equals("02")){
			operation_pattern3 = "混动";
		}else if(operation_patternStr3.equals("03")){
			operation_pattern3 = "燃油";	
		}else if(operation_patternStr3.equals("FE")){
			operation_pattern3 = "异常";	
		}else if(operation_patternStr3 == "FF"){
			operation_pattern3 = "无效";				
		}else{
			operation_pattern3 = operation_patternStr3+"（错）";
			System.out.println("operation_pattern3运行模式字符不正确");
		}
		
		if(ve_speedStr4.equals("FFFE")){//车速判断和赋值
			ve_speed4 = "异常";
		}else if(ve_speedStr4.equals("FFFF")){
			ve_speed4 = "无效";			
		}else{
			int a = Integer.valueOf(ve_speedStr4,16);//获取十进制数值
			if(a>=0&&a<=2200){
				double ve_speed = a/10;
				ve_speed4 = ve_speed+"";//去掉单位 km/h
			}else{
				ve_speed4 = ve_speedStr4+"（错）";
				System.out.println("取得值为："+a+",ve_speed4车速不在有效范围0-2200");
			}
		}
				
		if(mileageStr5.equals("FFFFFFFE")){//里程判断和赋值
			mileage5 = "异常";
		}else if(mileageStr5.equals("FFFFFFFF")){
			mileage5 = "无效";			
		}else{
			int a = Integer.valueOf(mileageStr5,16);//获取十进制数值
			if(a>=0&&a<=9999999){
				double mileage = a/10;
				mileage5 = mileage+"";//去掉单位 km
			}else{
				mileage5 = mileageStr5+"（错）";
				System.out.println("取得值为："+a+",mileage5里程不在有效范围0-9999999");
			}
		}
		
		if(total_voltageStr6.equals("FFFE")){//总电压判断和赋值
			total_voltage6 = "异常";
		}else if(total_voltageStr6.equals("FFFF")){
			total_voltage6 = "无效";			
		}else{
			int a = Integer.valueOf(total_voltageStr6,16);//获取十进制数值
			if(a>=0&&a<=10000){
				double total_voltage = a/10;
				total_voltage6 = total_voltage+"";//去掉单位 V
			}else{
				total_voltage6 = total_voltageStr6+"（错）";
				System.out.println("取得值为："+a+",total_voltage6总电压不在有效范围0-10000");
			}
		}
		
		if(total_electricityStr7.equals("FFFE")){//总电流判断和赋值
			total_electricity7 = "异常";
		}else if(total_electricityStr7.equals("FFFF")){
			total_electricity7 = "无效";			
		}else{
			int a = Integer.valueOf(total_electricityStr7,16);//获取十进制数值
			if(a>=0&&a<=20000){
				double total_electricity = a/10 -1000;
				total_electricity7 = total_electricity+"";	//去掉单位		
			}else{
				total_electricity7 = total_electricityStr7+"（错）";
				System.out.println("取得值为："+a+",total_electricity7总电流不在有效范围0-20000");
			}
		}
		
		if(socStr8.equals("FE")){//SOC判断和赋值
			soc8 = "异常";
		}else if(socStr8.equals("FF")){
			soc8 = "无效";			
		}else{
			int a = Integer.valueOf(socStr8,16);//获取十进制数值
			if(a>=0&&a<=100){
				soc8 = Integer.toString(a)+"";//去掉单位%
			}else{
				soc8 = socStr8+"（错）";
				System.out.println("取得值为："+a+",soc8不在有效范围0-100");
			}
		}
		
		if(dcdc_statusStr9.equals("01")){//DC-DC状态判断和赋值
			dcdc_status9 = "工作";
		}else if(dcdc_statusStr9.equals("02")){
			dcdc_status9 = "断开";
		}else if(dcdc_statusStr9.equals("FE")){
			dcdc_status9 = "异常";	
		}else if(dcdc_statusStr9.equals("FF")){
			dcdc_status9 = "无效";				
		}else{
			dcdc_status9 = dcdc_statusStr9+"（错）";
			System.out.println("dcdc_status9  DC-DC状态字符不正确");
		}
		
		grade10 = hexadecimalToBinary(gradeStr10);	//档位赋值
		
		int resistanceInt11 = Integer.valueOf(resistanceStr11,16);
		if(resistanceInt11>=0&&resistanceInt11<=60000){//电阻判断和赋值
			resistance11 = Integer.toString(resistanceInt11)+"";//去掉单位 kΩ
		}else{
			resistance11 = resistanceStr11+"（错）";
			System.out.println("绝缘电阻不在正常范围0-60000，且值为："+resistanceInt11);
		}
		
		if(accelerate_lengthStr12.equals("FE")){//加速踏板行程值判断和赋值
			accelerate_length12 = "异常";
		}else if(accelerate_lengthStr12.equals("FF")){
			accelerate_length12 = "无效";	
		}else{
			int a = Integer.valueOf(accelerate_lengthStr12,16);//获取十进制数值
			if(a>=0&&a<=100){
				accelerate_length12 = Integer.toString(a)+"";//去掉单位 %
			}else{
				accelerate_length12 = accelerate_lengthStr12+"（错）";
				System.out.println("accelerate_length12加速踏板行程值字符不在正常范围0-100");
			}
		}
		
		if(brake_lengthStr13.equals("FE")){//制动踏板行程值判断和赋值
			brake_length13 = "异常";
		}else if(brake_lengthStr13.equals("FF")){
			brake_length13 = "无效";	
		}else if(brake_lengthStr13.equals("00")){
			brake_length13 = "制动关";	
		}else if(brake_lengthStr13.equals("65")){
			brake_length13 = "制动有效";	
		}else{
			int a = Integer.valueOf(brake_lengthStr13,16);//获取十进制数值
			if(a>=0&&a<=100){
				brake_length13 = Integer.toString(a)+"";//去掉单位 %
			}else{
				brake_length13 = brake_lengthStr13+"（错）";
				System.out.println("brake_length13制动踏板行程值字符不正确");
			}
		}
				
		String bin = hexadecimalToBinary2(drive_statusStr14);//获取该字符代表的二进制字符串
		System.out.println("表示制动和驱动的二进制字符串bin："+bin);
		drive_status14 = bin.substring(2,3);
		if(drive_status14.equals("0")){//驱动状态判断和赋值
			drive_status14 = "无驱动力";
		}else if(drive_status14.equals("1")){
			drive_status14 = "有驱动力";
		}
		
		brake_status15 =bin.substring(3,4);
		if(brake_status15.equals("0")){//制动状态判断和赋值
			brake_status15 = "无制动力";
		}else if(brake_status15.equals("1")){
			brake_status15 = "有制动力";
		}

		System.out.println("--------------直接从字符串中获取的十六进制字符值----------");
		System.out.println("ve_statusStr1车辆状态:"+ve_statusStr1);
		System.out.println("charge_statusStr2充电状态:"+charge_statusStr2);
		System.out.println("operation_patternStr3运行模式:"+operation_patternStr3);
		System.out.println("ve_speedStr4车速:"+ve_speedStr4);
		System.out.println("mileageStr5里程:"+mileageStr5);
		System.out.println("total_voltageStr6总电压:"+total_voltageStr6);
		System.out.println("total_electricityStr7总电流:"+total_electricityStr7);
		System.out.println("SOC:"+socStr8);
		System.out.println("dcdc_statusStr9  DC-DC状态:"+dcdc_statusStr9);
		System.out.println("gradeStr10档位:"+gradeStr10);
		System.out.println("resistanceStr11正极对地电阻:"+resistanceStr11);
		System.out.println("accelerate_lengthStr12加速踏板行程值:"+accelerate_lengthStr12);
		System.out.println("brake_lengthStr13制动踏板行程值:"+brake_lengthStr13);
		System.out.println("drive_statusStr14驱动状态:"+drive_statusStr14);
		System.out.println("brake_statusStr15制动状态:"+brake_statusStr15);
		
		System.out.println("==========================================↓↓↓↓以下是整车数据Tb_vehicle的analysis执行后赋值=============================================");
		System.out.println("ve_status1车辆状态:"+ve_status1);
		System.out.println("charge_status2充电状态:"+charge_status2);
		System.out.println("operation_pattern3运行模式:"+operation_pattern3);
		System.out.println("ve_speed4车速:"+ve_speed4);
		System.out.println("mileage5里程:"+mileage5);
		System.out.println("total_voltage6总电压:"+total_voltage6);
		System.out.println("total_electricity7总电流:"+total_electricity7);
		System.out.println("SOC8:"+soc8);
		System.out.println("dcdc_status9  DC-DC状态:"+dcdc_status9);
		System.out.println("grade10档位:"+grade10);
		System.out.println("resistance11正极对地电阻:"+resistance11);
		System.out.println("accelerate_length12加速踏板行程值:"+accelerate_length12);
		System.out.println("brake_length13制动踏板行程值:"+brake_length13);
		System.out.println("drive_status14驱动状态:"+drive_status14);
		System.out.println("brake_status15制动状态:"+brake_status15);
		System.out.println("==========================================↑↑↑↑是整车数据Tb_vehicle的analysis执行后赋值=================================================");
	}
	
	/**
	 * 找字符对应的挡位
	 * @param str
	 * @return
	 */
	public static String hexadecimalToBinary(String str){
		switch (str) {
		case "0":
			//System.out.println(ch);
		   return "空挡";
		case "1":
			//System.out.println(ch);
			return "1挡";
		case "2":
			//System.out.println(ch);
			return "2挡";
		case "3":
			//System.out.println(ch);
			return "3挡";
		case "4":
			//System.out.println(ch);
			return "4挡";
		case "5":
			//System.out.println(ch);
			return "5挡";
		case "6":
			//System.out.println(ch);
			return "6挡";		
		case "D":
			//System.out.println(ch);
			return "倒挡";
		case "d":
			//System.out.println(ch);
			return "倒挡";
		case "E":
			//System.out.println(ch);
			return "自动D挡";
		case "e":
			//System.out.println(ch);
			return "自动D挡";
		case "F":
			//System.out.println(ch);
			return "停车P挡";		
		case "f":
			//System.out.println(ch);
			return "停车P挡";		
		default:
		    System.out.println("出错：该十六进制找不到对应的二进制"+",值为："+str);
		    return null;
		}
	}
	
	/**
	 * 找到16进制字符对应的二进制字符串
	 * @param str
	 * @return
	 */
	public static String hexadecimalToBinary2(String str){
		switch (str) {
		case "0":
			//System.out.println(ch);
		   return "0000";
		case "1":
			//System.out.println(ch);
			return "0001";
		case "2":
			//System.out.println(ch);
			return "0010";
		case "3":
			//System.out.println(ch);
			return "0011";
		case "4":
			//System.out.println(ch);
			return "0100";
		case "5":
			//System.out.println(ch);
			return "0101";
		case "6":
			//System.out.println(ch);
			return "0110";
		case "7":
			//System.out.println(ch);
			return "0111";
		case "8":
			//System.out.println(ch);
			return "1000";
		case "9":
			//System.out.println(ch);
			return "1001";
		case "A":
			//System.out.println(ch);
			return "1010";
		case "a":
			//System.out.println(ch);
			return "1010";
		case "B":
			//System.out.println(ch);
			return "1011";
		case "b":
			//System.out.println(ch);
			return "1011";
		case "C":
			//System.out.println(ch);
			return "1100";
		case "c":
			//System.out.println(ch);
			return "1100";
		case "D":
			//System.out.println(ch);
			return "1101";
		case "d":
			//System.out.println(ch);
			return "1101";
		case "E":
			//System.out.println(ch);
			return "1110";
		case "e":
			//System.out.println(ch);
			return "1110";
		case "F":
			//System.out.println(ch);
			return "1111";		
		case "f":
			//System.out.println(ch);
			return "1111";		
		default:
		    System.out.println("出错：该十六进制找不到对应的二进制");
		    return null;
		}
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

