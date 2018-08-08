package testDeom;

import database.Tb_alert;
import database.Tb_electric_motor;
import database.Tb_extremum;
import database.Tb_history_message;
import database.Tb_location;
import database.Tb_login_out;
import database.Tb_vehicle;

/**
 * 该函数用于执行所有数据表的分析
 * @author zuck zhao
 *
 */
public class DoAll {

	/**
	 * 1.判断登录、登出、实时信息、补发信息
	 * @param args
	 */
	public static void main(String[] args) {
		String currentLogin = "23 23 01 FE 4C 41 39 35 42 37 33 42 58 47 31 4C 43 30 38 30 30 01 00 1E 11 07 18 12 07 11 00 17 38 39 38 36 30 33 31 36 34 33 32 30 32 32 39 30 30 32 36 30 01 00 D3";
		//一个电机
		String currentTime = "23 23 02 FE 4C 41 39 35 43 38 36 48 30 46 31 4C 43 30 30 30 31 01 02 25 11 07 18 0D 09 1C 01 01 03 01 00 00 00 01 83 B9 15 B8 27 39 5B 01 00 0C 04 00 00 02 01 01 01 53 4E 20 4E 20 64 15 E0 27 10 05 00 06 D0 F5 BD 01 5A 0A 00 06 01 A1 0C FB 01 6E 0C CC 01 46 4A 01 1D 44 07 03 00 00 00 01 00 00 00 00 08 01 01 15"+ 
				"B8 27 39 00 A8 00 01 A8 0C F5 0C F8 0C F8 0C F7 0C F5 0C F4 0C F3 0C F4 0C F5 0C F8 0C F8 0C F7 0C F8 0C F0 0C ED 0C ED 0C EE 0C EB 0C ED 0C EB 0C EE 0C ED 0C "+
				"EB 0C F5 0C F9 0C F9 0C F9 0C F9 0C F9 0C F4 0C F5 0C F8 0C F8 0C F7 0C F9 0C F9 0C F5 0C F7 0C F8 0C F9 0C F8 0C F7 0C F7 0C F0 0C F0 0C F8 0C F7 0C F9 0C F8 "+
				"0C F7 0C EE 0C EE 0C ED 0C EE 0C EE 0C F0 0C F0 0C ED 0C EE 0C EE 0C F1 0C F1 0C EE 0C F0 0C F1 0C F1 0C F0 0C F0 0C F1 0C ED 0C F7 0C F8 0C F9 0C F5 0C F9 0C"+
				"FA 0C F2 0C F4 0C F7 0C F8 0C F9 0C F7 0C F9 0C F5 0C F4 0C F8 0C F7 0C F8 0C F8 0C F8 0C EF 0C F3 0C F8 0C F7 0C F9 0C F8 0C F8 0C F4 0C F4 0C F6 0C F6 0C F6 "+
				"0C F7 0C F4 0C F6 0C F4 0C F3 0C F4 0C F0 0C CC 0C F0 0C F1 0C F0 0C F0 0C F0 0C F0 0C F0 0C F7 0C F8 0C F5 0C F9 0C F8 0C F7 0C F3 0C F3 0C F8 0C F8 0C F8 0C "+
				"F8 0C F7 0C F5 0C F7 0C F9 0C F8 0C F8 0C F8 0C F8 0C F2 0C F3 0C F8 0C F8 0C F7 0C F8 0C F8 0C F7 0C F3 0C F0 0C F0 0C EE 0C F0 0C E5 0C EE 0C F0 0C F1 0C EE "+
				"0C F8 0C F9 0C F8 0C F9 0C F8 0C FB 0C F2 0C F7 0C F8 0C F7 0C F8 0C F8 0C F5 09 01 01 00 78 47 46 45 45 46 47 47 49 49 47 46 46 46 47 47 47 46 45 45 45 46 46 "+
				"46 46 46 46 45 45 44 45 46 46 45 45 47 47 46 45 45 45 46 46 46 47 46 46 46 46 47 47 49 48 47 46 46 46 46 47 47 48 48 47 47 46 46 46 46 46 48 4A 46 46 46 46 46 "+
				"46 47 46 45 46 46 46 46 46 46 45 45 45 46 46 45 45 46 45 46 45 45 44 46 46 46 46 47 46 47 46 45 46 47 47 47 47 46 45 45 47 46 47 48 48 34";
		//以下两个都是停车充电，没有电机数据
		String currentTime4 ="23 23 02 FE 4C 43 30 36 53 32 34 53 33 48 30 39 39 31 33 32 32 01 01 47 11 09 0F 13 2D 31 01 02 01 01 00 00 00 01 21 65 12 FC 22 17 56 01 00 0C 84 00 00 05 00 06 FF F0 E6 01 F6 01 31 06 01 4C 0D 35 01 00 FF FF 01 00 FF 01 00 FF 07 00 00 00 00 00 00 00 00 00 08 01 01 12 FC 22 17 01 20 00 C9 58 0D 32 0D 2F 0D 2F 0D 30 0D 31 0D 30 0D 30 0D 2F 0D 2E 0D 30 0D 30 0D 2F 0D 31 0D 31 0D 2B 0D 34 0D 34 0D 30 0D 2F 0D 30 0D 31 0D 30 0D 30 0D 2F 0D 2E 0D 30 0D 30 0D 30 0D 31 0D 30 0D 2B 0D 32 0D 32 0D 2F 0D 31 0D 30 0D 31 0D 31 0D 2F 0D 2F 0D 30 0D 31 0D 31 0D 34 0D 32 0D 30 0D 30 0D 2F 0D 2F 0D 31 0D 31 0D 33 0D 34 0D 2F 0D 30 0D 30 0D 2F 0D 32 0D 30 0D 33 0D 33 0D 32 0D 31 0D 31 0D 30 0D 30 0D 31 0D 34 0D 34 0D 30 0D 30 0D 30 0D 31 0D 32 0D 32 0D 35 0D 34 0D 31 0D 30 0D 31 0D 2F 0D 31 0D 31 0D 34 0D 34 0D 31 0D 30 0D 30 09 01 01 00 48 4A 49 4A 4A 49 4A 4B 49 4B 4A 49 4A 49 49 49 49 48 49 47 47 46 46 47 46 46 47 46 46 47 46 46 47 47 47 47 47 4B 49 4B 4A 49 4A 4A 49 4A 49 49 49 49 49 49 49 49 49 47 48 47 47 47 47 47 47 47 46 47 46 46 47 46 46 47 46 D0 ";
		String currentTime2 ="23 23 02 FE 4C 43 30 32 53 36 34 52 37 48 31 30 30 30 31 34 39 01 00 81 11 07 1D 0F 02 07 01 02 01 01 00 00 00 00 0A 22 1A 81 25 DA 29 01 00 07 E4 00 00 05 00 06 D0 D2 4D 01 59 FF 98 06 01 00 FF FF 01 00 FF FF 01 00 FF 01 00 FF 07 00 00 00 00 00 00 00 00 00 08 01 01 1A 81 25 DA 00 CC 00 C9 04 0C FC 0C FC 0C FB 0C FD 09 01 01 00 2A 4C 4B 4C 4C 4C 4C 4C 4C 4D 4C 4B 4D 4C 4B 4C 4C 4B 4C 4C 4B 4C 4C 4B 4C 4C 4B 4C 4C 4B 4C 4C 4C 4C 4C 4B 4C 4D 4C 4D 4D 4C 4C 89 ";	
		//两个电机
		String currentTime3 ="23 23 02 FE 4C 43 30 36 53 32 34 53 33 48 30 39 39 31 33 32 32 01 01 61 11 07 0B 0E 0B 07 01 01 03 01 00 00 00 00 00 07 12 A2 27 26 5A 01 00 0B AB 00 00 02 02 01 04 48 4E 20 4E 20 64 00 00 00 00 02 01 46 4E 20 4E 20 64 00 00 00 00 05 00 07 16 E0 06 01 E4 "+
		                     "09 EE 06 01 00 FF FF 01 00 FF FF 01 00 FF 01 00 FF 07 00 00 00 00 00 00 00 00 00 "+
				             "08 01 01 12 A2 27 26 01 20 00 C9 58 0C F6 0C F5 0C F5 0C F6 0C F6 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F2 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F1 0C F5 0C F6 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F6 0C F7 0C F6 0C F6 0C F6 0C F6 0C F7 0C F6 0C F7 0C F6 0C F7 0C F6 0C F7 0C F6 0C F7 0C F7 0C F7 0C F7 0C F7 0C F6 0C F7 0C F6 0C F7 0C F6 0C F7 0C F6 0C F6 0C F5 0C F6 0C F7 0C F7 0C F7 0C F7 0C F7 0C F7 0C F6 0C F7 0C F6 0C F7 0C F7 0C F6 0C F7 0C F7 0C F6 0C F6"+
				            " 09 01 01 00 48 45 44 45 45 44 45 45 44 46 46 45 46 45 44 45 45 44 45 48 47 48 48 47 48 48 47 48 48 47 47 47 46 48 47 46 48 45 44 45 45 44 45 45 44 45 45 45 45 45 44 45 45 44 45 48 46 48 48 46 48 48 47 48 48 47 48 48 47 48 48 47 48 34 ";	
		String currentLogout = "23 23 04 FE 4C 41 39 35 42 37 33 42 58 47 31 4C 43 30 38 30 30 01 00 08 11 07 18 0F 36 0E 00 10 F6 ";
		String RemoveBlank = currentLogout.replace(" ", "");//去除空格
		System.out.println("传入字符去除空格后的报文总长度："+RemoveBlank.length());
		String minglingbiaozhi = RemoveBlank.substring(4,6); //命令标识，用于判断登录、登出、实时信息、补发信息
		
		if(minglingbiaozhi.equals("01")){//登入时候已经存在了历史报文插入的情况
			System.out.println("命令标识为01，为车辆登入信息");
			Tb_login_out tb_login_out = new Tb_login_out();
			tb_login_out.analysis(RemoveBlank);
			tb_login_out.insert();
//			Tb_history_message tb_history_message = new Tb_history_message();//执行历史报文
//			tb_history_message.analysis(RemoveBlank);
//			tb_history_message.insert();
			System.out.println("登入信息处理完毕！！！");
		}else if(minglingbiaozhi.equals("02")){
			System.out.println("命令标识为02，为实时信息上报");
			Tb_vehicle tb_vehicle = new Tb_vehicle();//执行整车信息
			tb_vehicle.analysis(RemoveBlank);
			tb_vehicle.insert();
			
			Tb_electric_motor tb_electric_motor = new Tb_electric_motor();//执行驱动电机
			tb_electric_motor.analysis(RemoveBlank);
			tb_electric_motor.insert();
			
			Tb_location tb_location = new Tb_location();//执行位置信息
			tb_location.analysis(RemoveBlank);
			tb_location.insert();
			
			Tb_extremum tb_extremum = new Tb_extremum();//执行极值数据
			tb_extremum.analysis(RemoveBlank);
			tb_extremum.insert();
			
			Tb_alert tb_alert = new Tb_alert();//执行报警数据
			tb_alert.analysis(RemoveBlank);
			tb_alert.insert();
			
			Tb_history_message tb_history_message = new Tb_history_message();//执行历史报文
			tb_history_message.analysis(RemoveBlank);
			tb_history_message.insert();
			
			
			System.out.println("实时信息处理完毕！！！");
		}else if(minglingbiaozhi.equals("03")){
			System.out.println("命令标识为03，为补发信息上报");
			Tb_vehicle tb_vehicle = new Tb_vehicle();//执行整车信息
			tb_vehicle.analysis(RemoveBlank);
			tb_vehicle.insert();
			
			Tb_electric_motor tb_electric_motor = new Tb_electric_motor();//执行驱动电机
			tb_electric_motor.analysis(RemoveBlank);
			tb_electric_motor.insert();
			
			Tb_location tb_location = new Tb_location();//执行位置信息
			tb_location.analysis(RemoveBlank);
			tb_location.insert();
			
			Tb_extremum tb_extremum = new Tb_extremum();//执行极值数据
			tb_extremum.analysis(RemoveBlank);
			tb_extremum.insert();
			
			Tb_alert tb_alert = new Tb_alert();//执行报警数据
			tb_alert.analysis(RemoveBlank);
			tb_alert.insert();
			
			Tb_history_message tb_history_message = new Tb_history_message();//执行历史报文
			tb_history_message.analysis(RemoveBlank);
			tb_history_message.insert();
			
			System.out.println("补发信息处理完毕！！！");
		}else if(minglingbiaozhi.equals("04")){
			System.out.println("命令标识为04，为车辆登出信息");
			Tb_login_out tb_login_out = new Tb_login_out();
			tb_login_out.analysis(RemoveBlank);
			tb_login_out.insert();
//			Tb_history_message tb_history_message = new Tb_history_message();//执行历史报文
//			tb_history_message.analysis(RemoveBlank);
//			tb_history_message.insert();
			System.out.println("登出信息处理完毕！！！");//登出信息处理完毕
		}else{
			System.out.println("出错：命令标识不是登录、登出、实时信息、补发信息中的任意一个");
		}

	}
	
	/**
	 * 和登录相关
	 * @param str
	 */
	public void doLogin(String str){
		
	}
	
	/**
	 * 和登出相关
	 * @param str
	 */
	public void doLogout(String str){
		
	}
	
	/**
	 * 和实时信息有关
	 * @param currentTime
	 */
	public void doCurrentTime(String currentTime){
		
	}

}
