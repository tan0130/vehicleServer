package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Tb_extremumBean;

/**
 * 极值数据tb_extremum相关操作
 * @author zuck zhao
 *
 */
public class Tb_extremum {
	

	/**
	 * 极值数据相关变量
	 */
	String vin = null;   //车架号
	String maxvol_package_number1 = null;   //单体电压最高值包序号
	String maxvol_monomer_number2 = null;   //单体电压最高值单体序号
	String max_voltage3 = null;			    //单体电压最高值
	String minvol_package_number4= null;    //单体电压最低值包序号
	String minvol_monomer_number5 = null;   //单体电压最低值单体序号
	String min_voltage6 = null;			    //单体电压最低值
	String maxtemp_package_number7 = null;  //最高温度值包序号
	String maxtemp_monomer_number8 = null;  //最高温度值单体序号
	String max_temperature9 = null;		    //最高温度值
	String mintemp_package_number10 = null; //最低温度值包序号
	String mintemp_monomer_number11 = null; //最低温度值单体序号
	String min_temperature12 = null;	    //最低温度值
/*	
	String maxvol_package_number1 = "1";   //单体电压最高值包序号
	String maxvol_monomer_number2 = "2";   //单体电压最高值单体序号
	String max_voltage3 = "3";			    //单体电压最高值
	String minvol_package_number4= "4";    //单体电压最低值包序号
	String minvol_monomer_number5 = "5";   //单体电压最低值单体序号
	String min_voltage6 = "6";			    //单体电压最低值
	String maxtemp_package_number7 = "7";  //最高温度值包序号
	String maxtemp_monomer_number8 = "8";  //最高温度值单体序号
	String max_temperature9 = "9";		    //最高温度值
	String mintemp_package_number10 = "10"; //最低温度值包序号
	String mintemp_monomer_number11 = "11"; //最低温度值单体序号
	String min_temperature12 = "12";	    //最低温度值

*/	public void insert(){
	/**
	 * 数据库变量
	 */
	Connection con = ConnectionManager.getInstance().getConnection();
	Statement stmt = null;
	ResultSet rs = null;
		String sql = "insert into tb_extremum(vin,maxvol_package_number1,maxvol_monomer_number2,max_voltage3,minvol_package_number4,minvol_monomer_number5,min_voltage6,"
						+ "maxtemp_package_number7,maxtemp_monomer_number8,max_temperature9,mintemp_package_number10,mintemp_monomer_number11,min_temperature12) "
						+ "values('"+vin+"','"+maxvol_package_number1+"','"+maxvol_monomer_number2+"','"+max_voltage3+"','"+minvol_package_number4+"','"+minvol_monomer_number5+"',  "
						+ "  '"+min_voltage6+"','"+maxtemp_package_number7+"','"+maxtemp_monomer_number8+"','"+max_temperature9+"',  "
						+ "'"+mintemp_package_number10+"','"+mintemp_monomer_number11+"','"+min_temperature12+"')";
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
			System.out.println("极值数据tb_extremum的insert执行完毕，关闭所有连接，插入成功");
		}
		
	}
	
	/**
	 * 查询数据
	 */
	public Tb_extremumBean select(String vin){
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from tb_extremum where vin ='"+vin+"' order by id desc LIMIT 1";
		Tb_extremumBean Bean = new Tb_extremumBean();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);   //插入不能使用executeQuery()
			while(rs.next()){
				Bean.setMaxvol_package_number1(rs.getString("maxvol_package_number1"));
				Bean.setMaxvol_monomer_number2(rs.getString("maxvol_monomer_number2"));
				Bean.setMax_voltage3(rs.getString("max_voltage3"));
				Bean.setMinvol_package_number4(rs.getString("minvol_package_number4"));
				Bean.setMinvol_monomer_number5(rs.getString("minvol_monomer_number5"));
				Bean.setMin_voltage6(rs.getString("min_voltage6"));
				Bean.setMaxtemp_package_number7(rs.getString("maxtemp_package_number7"));
				Bean.setMaxtemp_monomer_number8(rs.getString("maxtemp_monomer_number8"));
				Bean.setMax_temperature9(rs.getString("max_temperature9"));
				Bean.setMintemp_package_number10(rs.getString("mintemp_package_number10"));
				Bean.setMintemp_monomer_number11(rs.getString("mintemp_monomer_number11"));
				Bean.setMin_temperature12(rs.getString("min_temperature12"));
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
			System.out.println("极值数据tb_extremum的select执行完毕，关闭所有连接");
		}
		return Bean;
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
		String chongdianzhuangtai = RemoveBlank.substring(64,66);	//充电状态，用于判断移位
		String dianjigeshu = RemoveBlank.substring(104,106);	//电机个数，用于判断移位
		int m = 0;//数据位偏移位数
		
		System.out.println("充电状态:"+chongdianzhuangtai+",电机个数："+dianjigeshu);
		if(chongdianzhuangtai.equals("01")){//停车状态，没有电机数据，移位-28，即往前移位28位，共14个字节
			m = -28;
		}else if(dianjigeshu.equals("01")){//不是停车状态，有一个电机，不做移位
			m = 0;
		}else if(dianjigeshu.equals("02")){//不是停车状态，有两个电机，移位24
			m = 24;
		}
		System.out.println("偏移量："+m);
		
		String maxvol_package_numberStr1   = RemoveBlank.substring(152+m,154+m);   //单体电压最高值包序号
		String maxvol_monomer_numberStr2   = RemoveBlank.substring(154+m,156+m);   //单体电压最高值单体序号
		String max_voltageStr3             = RemoveBlank.substring(156+m,160+m);   //单体电压最高值
		String minvol_package_numberStr4   = RemoveBlank.substring(160+m,162+m);   //单体电压最低值包序号
		String minvol_monomer_numberStr5   = RemoveBlank.substring(162+m,164+m);   //单体电压最低值单体序号
		String min_voltageStr6             = RemoveBlank.substring(164+m,168+m);   //单体电压最低值
		String maxtemp_package_numberStr7  = RemoveBlank.substring(168+m,170+m);   //最高温度值包序号
		String maxtemp_monomer_numberStr8  = RemoveBlank.substring(170+m,172+m);   //最高温度值单体序号
		String max_temperatureStr9         = RemoveBlank.substring(172+m,174+m);   //最高温度值
		String mintemp_package_numberStr10 = RemoveBlank.substring(174+m,176+m);   //最低温度值包序号
		String mintemp_monomer_numberStr11 = RemoveBlank.substring(176+m,178+m);   //最低温度值单体序号
		String min_temperatureStr12        = RemoveBlank.substring(178+m,180+m);   //最低温度值
		
		if(maxvol_package_numberStr1.equals("FE")){//单体电压最高值包序号判断和赋值
			maxvol_package_number1 = "异常";
		}else if(maxvol_package_numberStr1.equals("FF")){
			maxvol_package_number1 = "无效";
		}else{
			int a = Integer.valueOf(maxvol_package_numberStr1,16);//获取十进制数值
			if(a>=0&&a<=250){
				maxvol_package_number1 = a+"";
			}else{
				maxvol_package_number1 = maxvol_package_numberStr1+"（错）";
				System.out.println("取得值为："+a+",单体电压最高值包序号1不在有效范围0-250");
			}
		}
		
		if(maxvol_monomer_numberStr2.equals("FE")){//单体电压最高值单体序号判断和赋值
			maxvol_monomer_number2 = "异常";
		}else if(maxvol_monomer_numberStr2.equals("FF")){
			maxvol_monomer_number2 = "无效";
		}else{
			int a = Integer.valueOf(maxvol_monomer_numberStr2,16);//获取十进制数值
			if(a>=0&&a<=250){
				maxvol_monomer_number2 = a+"";
			}else{
				maxvol_monomer_number2 = maxvol_monomer_numberStr2+"（错）";
				System.out.println("取得值为："+a+",单体电压最高值单体序号2不在有效范围0-250");
			}
		}
		
		if(max_voltageStr3.equals("FFFE")){//单体电压最高值判断和赋值
			max_voltage3 = "异常";
		}else if(max_voltageStr3.equals("FFFF")){
			max_voltage3 = "无效";
		}else{
			int a = Integer.valueOf(max_voltageStr3,16);//获取十进制数值
			if(a>=0&&a<=15000){
				double b = a/1000;
				max_voltage3 = b+"";//去掉单位 V
			}else{
				max_voltage3 = max_voltageStr3+"（错）";
				System.out.println("取得值为："+a+",单体电压最高值3不在有效范围0-15000");
			}
		}
		
		if(minvol_package_numberStr4.equals("FE")){//单体电压最低值包序号判断和赋值
			minvol_package_number4 = "异常";
		}else if(minvol_package_numberStr4.equals("FF")){
			minvol_package_number4 = "无效";
		}else{
			int a = Integer.valueOf(minvol_package_numberStr4,16);//获取十进制数值
			if(a>=0&&a<=250){
				minvol_package_number4 = a+"";
			}else{
				minvol_package_number4 = minvol_package_numberStr4+"（错）";
				System.out.println("取得值为："+a+",单体电压最低值包序号4不在有效范围0-250");
			}
		}
		
		if(minvol_monomer_numberStr5.equals("FE")){//单体电压最低值单体序号判断和赋值
			minvol_monomer_number5 = "异常";
		}else if(minvol_monomer_numberStr5.equals("FF")){
			minvol_monomer_number5 = "无效";
		}else{
			int a = Integer.valueOf(minvol_monomer_numberStr5,16);//获取十进制数值
			if(a>=0&&a<=250){
				minvol_monomer_number5 = a+"";
			}else{
				minvol_monomer_number5 = minvol_monomer_numberStr5+"（错）";
				System.out.println("取得值为："+a+",单体电压最低值单体序号5不在有效范围0-250");
			}
		}
		
		if(min_voltageStr6.equals("FFFE")){//单体电压最低值判断和赋值
			min_voltage6 = "异常";
		}else if(min_voltageStr6.equals("FFFF")){
			min_voltage6 = "无效";
		}else{
			int a = Integer.valueOf(min_voltageStr6,16);//获取十进制数值
			if(a>=0&&a<=15000){
				double b = a/1000;
				min_voltage6 = b+"";//去掉单位 V
			}else{
				min_voltage6 = min_voltageStr6+"（错）";
				System.out.println("取得值为："+a+",单体电压最低值6不在有效范围0-15000");
			}
		}
		
		if(maxtemp_package_numberStr7.equals("FE")){//最高温度值包序号判断和赋值
			maxtemp_package_number7 = "异常";
		}else if(maxtemp_package_numberStr7.equals("FF")){
			maxtemp_package_number7 = "无效";
		}else{
			int a = Integer.valueOf(maxtemp_package_numberStr7,16);//获取十进制数值
			if(a>=0&&a<=250){
				maxtemp_package_number7 = a+"";
			}else{
				maxtemp_package_number7 = maxtemp_package_numberStr7+"（错）";
				System.out.println("取得值为："+a+",最高温度值包序号7不在有效范围0-250");
			}
		}
		
		if(maxtemp_monomer_numberStr8.equals("FE")){//最高温度值单体序号判断和赋值
			maxtemp_monomer_number8 = "异常";
		}else if(maxtemp_monomer_numberStr8.equals("FF")){
			maxtemp_monomer_number8 = "无效";
		}else{
			int a = Integer.valueOf(maxtemp_monomer_numberStr8,16);//获取十进制数值
			if(a>=0&&a<=250){
				maxtemp_monomer_number8 = a+"";
			}else{
				maxtemp_monomer_number8 = maxtemp_monomer_numberStr8+"（错）";
				System.out.println("取得值为："+a+",最高温度值单体序号8不在有效范围0-250");
			}
		}
				
		if(max_temperatureStr9.equals("FE")){//最高温度值判断和赋值
			max_temperature9 = "异常";
		}else if(max_temperatureStr9.equals("FF")){
			max_temperature9 = "无效";
		}else{
			int a = Integer.valueOf(max_temperatureStr9,16);//获取十进制数值
			if(a>=0&&a<=250){
				double b = a-40;
				max_temperature9 = b+"";//去掉单位 ℃
			}else{
				max_temperature9 = max_temperatureStr9+"（错）";
				System.out.println("取得值为："+a+",最高温度值9不在有效范围0-250");
			}
		}
		
		if(mintemp_package_numberStr10.equals("FE")){//最低温度值包序号判断和赋值
			mintemp_package_number10 = "异常";
		}else if(mintemp_package_numberStr10.equals("FF")){
			mintemp_package_number10 = "无效";
		}else{
			int a = Integer.valueOf(mintemp_package_numberStr10,16);//获取十进制数值
			if(a>=0&&a<=250){
				mintemp_package_number10 = a+"";
			}else{
				mintemp_package_number10 = mintemp_package_numberStr10+"（错）";
				System.out.println("取得值为："+a+",最低温度值包序号10不在有效范围0-250");
			}
		}
		
		if(mintemp_monomer_numberStr11.equals("FE")){//最低温度值单体序号判断和赋值
			mintemp_monomer_number11 = "异常";
		}else if(mintemp_monomer_numberStr11.equals("FF")){
			mintemp_monomer_number11 = "无效";
		}else{
			int a = Integer.valueOf(mintemp_monomer_numberStr11,16);//获取十进制数值
			if(a>=0&&a<=250){
				mintemp_monomer_number11 = a+"";
			}else{
				mintemp_monomer_number11 = mintemp_monomer_numberStr11+"（错）";
				System.out.println("取得值为："+a+",最低温度值单体序号11不在有效范围0-250");
			}
		}
		
		
		if(min_temperatureStr12.equals("FE")){//最低温度值判断和赋值
			min_temperature12 = "异常";
		}else if(min_temperatureStr12.equals("FF")){
			min_temperature12 = "无效";
		}else{
			int a = Integer.valueOf(min_temperatureStr12,16);//获取十进制数值
			if(a>=0&&a<=250){
				double b = a-40;
				min_temperature12 = b+"";//去掉单位 ℃
			}else{
				min_temperature12 = min_temperatureStr12+"（错）";
				System.out.println("取得值为："+a+",最低温度值12不在有效范围0-250");
			}
		}
		
		System.out.println("==============================================↓↓↓↓以下是极值数据Tb_extremum的analysis执行后赋值=========================================");
		System.out.println("单体电压最高值包序号1:"  +maxvol_package_number1);
		System.out.println("单体电压最高值单体序号2:"+maxvol_monomer_number2);
		System.out.println("单体电压最高值3:"       +max_voltage3);
		System.out.println("单体电压最低值包序号4:"  +minvol_package_number4);
		System.out.println("单体电压最低值单体序号5:"+minvol_monomer_number5);
		System.out.println("单体电压最低值6:"       +min_voltage6);
		System.out.println("最高温度值包序号7:"     +maxtemp_package_number7);
		System.out.println("最高温度值单体序号8:"   +maxtemp_monomer_number8);
		System.out.println("最高温度值9:"          +max_temperature9);
		System.out.println("最低温度值包序号10:"    +mintemp_package_number10);
		System.out.println("最低温度值单体序号11:"  +mintemp_monomer_number11);
		System.out.println("最低温度值12:"         +min_temperature12);
		System.out.println("==============================================↑↑↑↑↑是极值数据Tb_extremum的analysis执行后赋值===========================================");
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



