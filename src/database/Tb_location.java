package database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.Tb_locationBean;

/**
 * 定位数据表tb_location相关操作
 * @author zuck zhao
 *
 */
public class Tb_location {
	

	/**
	 * 历史报文数据相关变量
	 */
	String vin = null;   //车架号
	String gps_status = null;			//GPS定位状态
	double longitude = 0;			//经度
	double latitude = 0;				//纬度

/*	
	String gps_status = "gps_status";	//GPS定位状态
	String longitude = "longitude";		//经度
	String latitude = "latitude";		//纬度
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
		String sql = "insert into tb_location(vin,gps_status,longitude,latitude)"
						+ "values('"+vin+"','"+gps_status+"','"+longitude+"','"+latitude+"')";//去掉了一个'，因为在和sql的符号冲突
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
			System.out.println("定位数据tb_location的insert执行完毕，关闭所有连接，插入成功");
		}
		
	}
	
	
	/**
	 * 目的：查询每个
	 */
	public String select2(){
		String AllNewLocation = null; //返回的每个车架号vin的最新一条记录的集合
		String sql = "SELECT * FROM tb_location,(SELECT vin,max(Id)Id  FROM tb_location GROUP BY vin)table2 WHERE tb_location.vin=table2.vin AND tb_location.Id=table2.Id order by tb_location.Id desc";
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;		
		String longitudeStr = null;
		String latitudeStr = null;
		String vinStr = null;
		List<String> list = new ArrayList<String>();
		int total = 0; //用来统计总共有多少行记录
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);   //插入不能使用executeQuery()			
			while(rs.next()){
				total++;
				vinStr = "\""+rs.getString("vin")+"\"";					
				longitudeStr ="\""+rs.getString("longitude")+"\"";
				latitudeStr = "\""+rs.getString("latitude")+"\"";
				String element ="{\"vin\":"+vinStr+",\"longitude\":"+longitudeStr+",\"latitude\":"+latitudeStr+"}";
				list.add(element);        //增加元素
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String listString = list.toString();
		String totalString = "\""+total+"\"";
		AllNewLocation = "{\"total\":"+totalString+",\"rows\":"+listString+"}";
		System.out.println("Tb_location的select2执行完毕");
		return AllNewLocation;
	}
	/**
	 * 查询数据
	 */
	public Tb_locationBean select(String vin){
		/**
		 * 数据库变量
		 */
		Connection con = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;		
		String sql = "select * from tb_location where vin ='"+vin+"' order by id desc LIMIT 1";
		Tb_locationBean Bean = new Tb_locationBean();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);   //插入不能使用executeQuery()
			while(rs.next()){
				Bean.setGps_status(rs.getString("gps_status"));
				Bean.setLongitude(rs.getString("longitude"));
				Bean.setLatitude(rs.getString("latitude"));
				System.out.println("执行Tb_location的select获取数据");
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
			System.out.println("定位数据tb_location的select执行完毕，关闭所有连接");
		}
		return Bean;
	}
	
	/**
	 * 从数据包找出相应的数据，并且赋值给相应的变量
	 */
	public void analysis(String currentTime){
		String RemoveBlank = currentTime.replace(" ", "");//去除空格
		
		String Stringvin =RemoveBlank.substring(8, 42);//vin赋值
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
		}else if(dianjigeshu.equals("02")){//不是停车状态，有两个电机，往后移位24
			m = 24;
		}		
		String gps_statusStr = RemoveBlank.substring(133+m,134+m);	//GPS定位状态，一个字节有两位，第一位为132，第二位为133，选第二位
		String longitudeStr = RemoveBlank.substring(134+m,142+m);	//经度
		String latitudeStr = RemoveBlank.substring(142+m,150+m);	//纬度

		System.out.println("GPS定位状态的十六进制字符是："+gps_statusStr);
		String gps_statusStr2 = hexadecimalToBinary2(gps_statusStr); //将16进制转换为2进制字符串
		System.out.println("GPS定位状态的二进制字符是："+gps_statusStr2);
		
		String gps_statusStr3 = gps_statusStr2.substring(3,4);  //获取二级制的第0位
		if(gps_statusStr3.equals("0")){ //判断定位状态和赋值
			gps_status = "有效定位";
		}else{
			gps_status = "无效定位";
		}
		
		System.out.println("-----------------------下面是经度赋值-----------");
		String longitudeStr1 = longitudeStr.substring(0,2);	//经度第一个字节
		String longitudeStr2 = longitudeStr.substring(2,4);	//经度第二个字节
		String longitudeStr3 = longitudeStr.substring(4,6);	//经度第三个字节
		String longitudeStr4 = longitudeStr.substring(6,8);	//经度第四个字节
		String longitudeStrAll = longitudeStr1+longitudeStr2+longitudeStr3+longitudeStr4;//换位置后重新拼接16进制
		System.out.println("经度未转换前的十六进制字符："+longitudeStrAll);
		long INTlong = Long.parseLong(longitudeStrAll, 16);
		double INTlong2 = INTlong;
		System.out.println("转换后十进制值："+INTlong);
		longitude = INTlong2/1000000; //此处就是经度值
//		System.out.println("PH:"+PH);
//		int PH1 = (int)(PH/3600);  //求度数值
//		double PH2 = (PH%3600)/60;//求分度值
//		if(PH2 < 0){//分度为如果小于零取反
//			PH2 = -PH2;
//		}
//		BigDecimal bigDecimal = new BigDecimal(PH2);  // 将double类型转为BigDecimal
//		double PH2Double = bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();// 保留四位小数,并且四舍五入
//		System.out.println("经度度数PH1:"+PH1+",经度分度PH2:"+PH2Double);
//		longitude = PH1+"°"+PH2Double+"'";	//组合度和分	
		System.out.println("处理完后的纬度值："+longitude);
				
		System.out.println("-----------------------下面是纬度转换-----------");
		String latitudeStr1 = latitudeStr.substring(0,2);	//纬度第一个字节
		
		String latitudeStr2 = latitudeStr.substring(2,4);	//纬度第二个字节
		String latitudeStr3 = latitudeStr.substring(4,6);	//纬度第三个字节
		String latitudeStr4 = latitudeStr.substring(6,8);	//纬度第四个字节
		String latitudeStrAll =latitudeStr1+latitudeStr2+latitudeStr3+latitudeStr4;//换位置后重新拼接16进制
		System.out.println("经度未转换前的十六进制字符："+latitudeStrAll);
//		System.out.println("纬度第一个字节："+latitudeStr1+" "+latitudeStr2+" "+latitudeStr3+" "+latitudeStr4);
//		System.out.println("拼接后latitudeStrAll："+latitudeStrAll);
		long INTlong3 = Long.parseLong(latitudeStrAll, 16);
	    double INTlong4 = INTlong3;
		System.out.println("转换后十进制值："+INTlong3);
		latitude = INTlong4/1000000; //此处求得INT值
//		System.out.println("PH:"+PH);//打印PH值
//		PH1 = (int)(PH/3600);  //求度数值
//		PH2 = (PH%3600)/60;//求分度值
//		if(PH2 < 0){//分度为如果小于零取反
//			PH2 = -PH2;//分度取反操作，保证小数点为始终为正值
//		}
//		bigDecimal = new BigDecimal(PH2);  // 将double类型转为BigDecimal
//		PH2Double = bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();// 保留四位小数,并且四舍五入
//		System.out.println("纬度度数PH1::"+PH1+",纬度分度PH2:"+PH2Double);
//		latitude = PH1+"°"+PH2Double+"'";	//组合度和分	
		System.out.println("处理完后的纬度值："+latitude);	
		System.out.println("====================================↓↓↓↓位置信息Tb_location的ansys执行后的赋值=========================================================");
		System.out.println("GPS定位状态gps_status:"+gps_status);
		System.out.println("经度longitude:"+longitude);
		System.out.println("纬度latitude:"+latitude);
		System.out.println("====================================↑↑↑↑位置信息Tb_location的ansys执行后的赋值=========================================================");
	}
	
	/**
	 * 找到16进制字符对应的二进制字符串
	 * @param str
	 * @return
	 */
	public static String hexadecimalToBinary2(String str){
		switch (str) {
		case "0":
		    return "0000";
		case "1":
			return "0001";
		case "2":
			return "0010";
		case "3":
			return "0011";
		case "4":
			return "0100";
		case "5":
			return "0101";
		case "6":
			return "0110";
		case "7":
			return "0111";
		case "8":
			return "1000";
		case "9":
			return "1001";
		case "A":
			return "1010";
		case "a":
			return "1010";
		case "B":
			return "1011";
		case "b":
			return "1011";
		case "C":
			return "1100";
		case "c":
			return "1100";
		case "D":
			return "1101";
		case "d":
			return "1101";
		case "E":
			return "1110";
		case "e":
			return "1110";
		case "F":
			return "1111";		
		case "f":
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



