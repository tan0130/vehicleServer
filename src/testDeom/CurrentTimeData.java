package testDeom;

/**
 * 对实时数据的各项进行提取
 * @author zuck zhao
 *
 */
public class CurrentTimeData {

	public static void main(String[] args) {
		String currentTime = "23 23 02 FE 4C 41 39 35 43 38 36 48 30 46 31 4C 43 30 30 30 31 01 02 25 11 07 18 0D 09 1C 01 01 03 01 00 00 00 01 83 B9 15 B8 27 39 5B 01 00 0C 04 00 0002 01 01 01 53 4E 20 4E 20 64 15 E0 27 10 05 00 06 D0 F5 BD 01 5A 0A 00 06 01 A1 0C FB 01 6E 0C CC 01 46 4A 01 1D 44 07 03 00 00 00 01 00 00 00 00 08 01 01 15"+ 
								"B8 27 39 00 A8 00 01 A8 0C F5 0C F8 0C F8 0C F7 0C F5 0C F4 0C F3 0C F4 0C F5 0C F8 0C F8 0C F7 0C F8 0C F0 0C ED 0C ED 0C EE 0C EB 0C ED 0C EB 0C EE 0C ED 0C "+
								"EB 0C F5 0C F9 0C F9 0C F9 0C F9 0C F9 0C F4 0C F5 0C F8 0C F8 0C F7 0C F9 0C F9 0C F5 0C F7 0C F8 0C F9 0C F8 0C F7 0C F7 0C F0 0C F0 0C F8 0C F7 0C F9 0C F8 "+
								"0C F7 0C EE 0C EE 0C ED 0C EE 0C EE 0C F0 0C F0 0C ED 0C EE 0C EE 0C F1 0C F1 0C EE 0C F0 0C F1 0C F1 0C F0 0C F0 0C F1 0C ED 0C F7 0C F8 0C F9 0C F5 0C F9 0C"+
								"FA 0C F2 0C F4 0C F7 0C F8 0C F9 0C F7 0C F9 0C F5 0C F4 0C F8 0C F7 0C F8 0C F8 0C F8 0C EF 0C F3 0C F8 0C F7 0C F9 0C F8 0C F8 0C F4 0C F4 0C F6 0C F6 0C F6 "+
								"0C F7 0C F4 0C F6 0C F4 0C F3 0C F4 0C F0 0C CC 0C F0 0C F1 0C F0 0C F0 0C F0 0C F0 0C F0 0C F7 0C F8 0C F5 0C F9 0C F8 0C F7 0C F3 0C F3 0C F8 0C F8 0C F8 0C "+
								"F8 0C F7 0C F5 0C F7 0C F9 0C F8 0C F8 0C F8 0C F8 0C F2 0C F3 0C F8 0C F8 0C F7 0C F8 0C F8 0C F7 0C F3 0C F0 0C F0 0C EE 0C F0 0C E5 0C EE 0C F0 0C F1 0C EE "+
								"0C F8 0C F9 0C F8 0C F9 0C F8 0C FB 0C F2 0C F7 0C F8 0C F7 0C F8 0C F8 0C F5 09 01 01 00 78 47 46 45 45 46 47 47 49 49 47 46 46 46 47 47 47 46 45 45 45 46 46 "+
								"46 46 46 46 45 45 44 45 46 46 45 45 47 47 46 45 45 45 46 46 46 47 46 46 46 46 47 47 49 48 47 46 46 46 46 47 47 48 48 47 47 46 46 46 46 46 48 4A 46 46 46 46 46 "+
								"46 47 46 45 46 46 46 46 46 46 45 45 45 46 46 45 45 46 45 46 45 45 44 46 46 46 46 47 46 47 46 45 46 47 47 47 47 46 45 45 47 46 47 48 48 34";
		/**
		 * currentTime3多了一个电机
		 */
		String currentTime3 = "23 23 02 FE 4C 41 39 35 43 38 36 48 30 46 31 4C 43 30 30 30 31 01 02 25 11 07 18 0D 09 1C 01 01 03 01 00 00 00 01 83 B9 15 B8 27 39 5B 01 00 0C 04 "+
"00 00 02 02 01 01 53 4E 20 4E 20 64 15 E0 27 10 01 01 53 4E 20 4E 20 64 15 E0 27 10 05 00 06 D0 F5 BD 01 5A 0A 00 06 01 A1 0C FB 01 6E 0C CC 01 46 4A 01 1D 44"+
"07 03 00 00 00 01 00 00 00 00 08 01 01 15 B8 27 39 00 A8 00 01 A8 0C F5 0C F8 0C F8 0C F7 "+
"0C F5 0C F4 0C F3 0C F4 0C F5 0C F8 0C F8 0C F7 0C F8 0C F0 0C ED 0C ED 0C EE 0C EB 0C ED 0C EB 0C EE 0C ED 0C EB 0C F5 0C F9 0C F9 0C F9 0C F9 0C F9 0C F4 0C "+
"F5 0C F8 0C F8 0C F7 0C F9 0C F9 0C F5 0C F7 0C F8 0C F9 0C F8 0C F7 0C F7 0C F0 0C F0 0C F8 0C F7 0C F9 0C F8 0C F7 0C EE 0C EE 0C ED 0C EE 0C EE 0C F0 0C F0 "+
"0C ED 0C EE 0C EE 0C F1 0C F1 0C EE 0C F0 0C F1 0C F1 0C F0 0C F0 0C F1 0C ED 0C F7 0C F8 0C F9 0C F5 0C F9 0C FA 0C F2 0C F4 0C F7 0C F8 0C F9 0C F7 0C F9 0C "+
"F5 0C F4 0C F8 0C F7 0C F8 0C F8 0C F8 0C EF 0C F3 0C F8 0C F7 0C F9 0C F8 0C F8 0C F4 0C F4 0C F6 0C F6 0C F6 0C F7 0C F4 0C F6 0C F4 0C F3 0C F4 0C F0 0C CC "+
"0C F0 0C F1 0C F0 0C F0 0C F0 0C F0 0C F0 0C F7 0C F8 0C F5 0C F9 0C F8 0C F7 0C F3 0C F3 0C F8 0C F8 0C F8 0C F8 0C F7 0C F5 0C F7 0C F9 0C F8 0C F8 0C F8 0C "+
"F8 0C F2 0C F3 0C F8 0C F8 0C F7 0C F8 0C F8 0C F7 0C F3 0C F0 0C F0 0C EE 0C F0 0C E5 0C EE 0C F0 0C F1 0C EE 0C F8 0C F9 0C F8 0C F9 0C F8 0C FB 0C F2 0C F7 "+
"0C F8 0C F7 0C F8 0C F8 0C F5 09 01 01 00 78 47 46 45 45 46 47 47 49 49 47 46 46 46 47 47 47 46 45 45 45 46 46 46 46 46 46 45 45 44 45 46 46 45 45 47 47 46 45 45 45 46 46 46 47 "+
"46 46 46 46 47 47 49 48 47 46 46 46 46 47 47 48 48 47 47 46 46 46 46 46 48 4A 46 46 46 46 46 46 47 46 45 46 46 46 46 46 46 45 45 45 46 46 45 45 46 45 46 45 45 "+
"44 46 46 46 46 47 46 47 46 45 46 47 47 47 47 46 45 45 47 46 47 48 48 34";
		
		
		String currentTime2 = currentTime3.replace(" ", "");		
		System.out.println(currentTime2);
		System.out.println("-----------------以下是获取的各个数据项---------------");
		String minglingbiaozhi = currentTime2.substring(4,6);//命令标识
		String yingdabiaozhi = currentTime2.substring(6, 8);//应答标识
		String weiyishibiema = currentTime2.substring(8, 42);//唯一识别码
		String jiamifangshi = currentTime2.substring(42, 44);//加密方式
		String danyuanchangdu = currentTime2.substring(44, 48);//单元长度
		String shujucaijishijian = currentTime2.substring(48, 60);//数据采集时间
		String zhengche = currentTime2.substring(60, 102);//整车数据
		String qudongdianji1 = currentTime2.substring(102, 130);//驱动电机数据

		String numberStr = currentTime2.substring(104, 106);
		int number = Integer.valueOf(numberStr,16); 
		System.out.println("驱动电机个数:"+number);
		if(number == 1 ){			
			String cheliangweizhi = currentTime2.substring(130, 150);//车辆位置
			String jizhi = currentTime2.substring(150, 180);//极值数据
			String baojing = currentTime2.substring(180, 192);//报警数据
			
			System.out.println("命令标识："+minglingbiaozhi);
			System.out.println("应答标识："+yingdabiaozhi);
			System.out.println("唯一识别码："+weiyishibiema);
			System.out.println("加密方式："+jiamifangshi);
			System.out.println("单元长度："+danyuanchangdu);
			System.out.println("数据采集时间："+shujucaijishijian);
			System.out.println("整车数据："+zhengche);
			System.out.println("驱动电机数据："+qudongdianji1);
			System.out.println("车辆位置："+cheliangweizhi);
			System.out.println("极值数据："+jizhi);
			System.out.println("报警数据："+baojing);
		}else{
			String qudongdianji2 = currentTime2.substring(130, 154);//车辆位置
			String cheliangweizhi = currentTime2.substring(154, 174);//车辆位置
			String jizhi = currentTime2.substring(174, 204);//极值数据
			String baojing = currentTime2.substring(204, 216);//报警数据
						
			System.out.println("命令标识："+minglingbiaozhi);
			System.out.println("应答标识："+yingdabiaozhi);
			System.out.println("唯一识别码："+weiyishibiema);
			System.out.println("加密方式："+jiamifangshi);
			System.out.println("单元长度："+danyuanchangdu);
			System.out.println("数据采集时间："+shujucaijishijian);
			System.out.println("整车数据："+zhengche);
			System.out.println("驱动电机数据："+qudongdianji1);
			System.out.println("驱动电机数据2(不包括头部前两个字节)："+qudongdianji2);
			System.out.println("车辆位置："+cheliangweizhi);
			System.out.println("极值数据："+jizhi);
			System.out.println("报警数据："+baojing);
		}
		
		
		
		
		
		
		
	}

}
