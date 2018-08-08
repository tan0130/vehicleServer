package database;

/**
 * 获取一个字符串，可以是登入登出，补发和实时的任何一种
 * @author zuck zhao
 *
 */
public class DoAll {

	/**
	 * 
	 * @param str为上传的数据
	 */
	public byte[] insert(String str) throws StringIndexOutOfBoundsException{
		byte [] returnbyte = new byte[1]; //用于返回给客户端的字节数组
		
		//String RemoveBlank = (str.replace(" ", "")).replace("\0","");//去除空格
		String RemoveBlank = str.replace(" ", "");//去除空格
		System.out.println("传入字符去除空格后的报文总长度："+RemoveBlank.length());
		System.out.println("传入字符去除空格后的报文："+RemoveBlank);
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
			returnbyte[0] = 0x01;
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
			returnbyte[0] = 0x01;
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
			returnbyte[0] = 0x01;

		}else if(minglingbiaozhi.equals("04")){
			System.out.println("命令标识为04，为车辆登出信息");
			Tb_login_out tb_login_out = new Tb_login_out();
			tb_login_out.analysis(RemoveBlank);
			tb_login_out.insert();
//			Tb_history_message tb_history_message = new Tb_history_message();//执行历史报文
//			tb_history_message.analysis(RemoveBlank);
//			tb_history_message.insert();
			System.out.println("登出信息处理完毕！！！");//登出信息处理完毕
			returnbyte[0] = 0x01;
		}else{
			System.out.println("出错：命令标识不是登录、登出、实时信息、补发信息中的任意一个");
			returnbyte[0] = 0x02;
		}
		
		return returnbyte;

	}
	
}
