package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Tb_alertBean;

/**
 * 该函数用来处理Tb_alert表(报警数据)
 * 1.将数据上传至数据库保存
 * 2.从数据库获取所需数据返回给客户端
 * @author zuck zhao
 *
 */
public class Tb_alert {
	
	/**
	 * 报警数据相关变量
	 */
	String vin = null;   //车架号
	String alter_level = null;				//报警等级
	String temperature_difference0 = null;	//0温度差异报警
	String battery_overheat1 = null;		//1电池高温报警
	String overvoltage2 = null;				//2车载储能装置类型过压报警
	String undervoltage3 = null;			//3车载储能装置类型欠压报警
	String soc_under4 = null;				//4SOC低报警
	String monomer_overvoltage5 = null;		//5单体电池过压报警
	String monomer_undervoltage6 = null;	//6单体电池欠压报警
	String soc_over7 = null;				//7SOC过高报警
	String soc_saltus8 = null;				//8SOC跳变报警
	String mismatch9 = null;				//9可充电储能系统不匹配报警
	String consistency10 = null;			//10电池单体一致性报警
	String insulation11 = null;				//11绝缘报警
	String dcdc_temperature12 = null;		//12DC-DC温度报警
	String brake_sys13 = null; 				//13制动系统报警
	String dcdc_status14 = null;			//14DC-DC状态报警
	String controller_temp15 = null;		//15驱动电机控制器温度报警
	String high_voltage_interlock16 = null;	//16高压互锁报警
	String motor_temp17 = null;				//17驱动电机温度报警
	String overcharge18 = null;				//18车载储能装置类型过充报警
	
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

		String sql = "insert into tb_alert(vin,temperature_difference0,battery_overheat1,overvoltage2,undervoltage3,soc_under4,monomer_overvoltage5,"
						+ "monomer_undervoltage6,soc_over7,soc_saltus8,mismatch9,consistency10,insulation11,dcdc_temperature12,brake_sys13,dcdc_status14,"
						+ "controller_temp15,high_voltage_interlock16,motor_temp17,overcharge18) "
						+ "values('"+vin+"','"+temperature_difference0+"','"+battery_overheat1+"','"+overvoltage2+"','"+undervoltage3+"','"+soc_under4+"','"+monomer_overvoltage5+"','"+monomer_undervoltage6+"',  "
						+ "  '"+soc_over7+"','"+soc_saltus8+"','"+mismatch9+"','"+consistency10+"','"+insulation11+"','"+dcdc_temperature12+"','"+brake_sys13+"',  "
						+ "'"+dcdc_status14+"','"+controller_temp15+"','"+high_voltage_interlock16+"','"+motor_temp17+"','"+overcharge18+"')";
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
			System.out.println("报警数据Tb_alert的insert执行完毕，关闭所有连接，插入成功");
		}
		
	}
	
	/**
	 * 查询数据
	 */
	public Tb_alertBean select(String vin){
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from tb_alert where vin ='"+vin+"' order by id desc LIMIT 1;";//查询最近一条记录
		Tb_alertBean Tabean = new Tb_alertBean();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);   
			while(rs.next()){
				Tabean.setTemperature_difference0(rs.getString("temperature_difference0"));
				System.out.println("Tb_alertBean的select获取数据，传值给Bean");
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
			System.out.println("报警数据Tb_alert的select执行完毕，关闭所有连接，成功获取数据");
		}
		return Tabean;
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
		String chongdianzhuangtai = RemoveBlank.substring(64,66);	//充电状态，用于判断移位
		String dianjigeshu = RemoveBlank.substring(104,106);	//电机个数，用于判断移位
		int m = 0;//数据位偏移位数
		
		System.out.println("充电状态:"+chongdianzhuangtai+",电机个数："+dianjigeshu);
		if(chongdianzhuangtai.equals("01")){//停车状态，没有电机数据，移位-28，即往前移位28位，共14个字节
			m = -28;
			System.out.println("停车状态，没有电机数据，移位-28");
		}else if(dianjigeshu.equals("01")){//不是停车状态，有一个电机，不做移位
			m = 0;
			System.out.println("不是停车状态，有一个电机，不做移位");
		}else if(dianjigeshu.equals("02")){//不是停车状态，有两个电机，往后移位24
			m = 24;
			System.out.println("不是停车状态，有两个电机，移位24");
		}
		
		String alter_levelStr = RemoveBlank.substring(182+m,184+m);	//报警等级
		String temperature_differenceStr0 = RemoveBlank.substring(191+m,192+m);	//0温度差异报警

		if(alter_levelStr.equals("00")){
			alter_level = "无故障";
		}else if(alter_levelStr.equals("01")){
			alter_level = "1级故障";
		}else if(alter_levelStr.equals("02")){
			alter_level = "2级故障";
		}else if(alter_levelStr.equals("03")){
			alter_level = "3级故障";
		}else if(alter_levelStr.equals("FE")){
			alter_level = "异常";
		}else if(alter_levelStr.equals("FF")){
			alter_level = "无效";
		}else{
			System.out.println("报警等级数据出现错误");
			alter_level = alter_levelStr;
		}		
		
		String temperature_differenceStr01 = hexadecimalToBinary2(temperature_differenceStr0);//将单个十六进制转换为二进制字符串
		String temperature_differenceStr02 = temperature_differenceStr01.substring(3,4);//取二进制字符串的最后一个数据位
		if(temperature_differenceStr02.equals("0")){
			temperature_difference0 = "正常";
		}else {
			temperature_difference0 = "报警";
		}
		
		System.out.println("=========================================↓↓↓↓以下是报警数据Tb_alert的analysis获取解析后赋值的报警数据====================================");
		System.out.println("报警等级："+alter_level);
		System.out.println("温度差异报警："+temperature_difference0);
		System.out.println("=========================================↑↑↑↑是报警数据Tb_alert的analysis获取解析后赋值的报警数据========================================");
	}

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




