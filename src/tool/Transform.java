package tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一切跟字符串转换有关的转换函数
 * 里面包括多个方法体
 * @author zuck zhao
 *
 */
public class Transform {
	
	public static void main(String[] args){
		String timeStr = "110718120711";
		Transform a = new Transform();
		a.Time(timeStr);
	}
	/**d
	 * 将110718120711类似的字符串转换为年月日时分秒，如2017-11-07 14:35:21
	 * 1.年前要补上"20"
	 * 2.每个字符表示一个数值
	 * @return
	 */
	public String Time(String timeStr){
		
		String yearStr = timeStr.substring(0, 2);
		String monthStr = timeStr.substring(2, 4);
		String dayStr = timeStr.substring(4, 6);
		String hourStr = timeStr.substring(6, 8);
		String minuteStr = timeStr.substring(8, 10);
		String secondStr = timeStr.substring(10, 12);
		
		int year = Integer.valueOf(yearStr,16);//将十六进制转换为十进制int类型
		int month = Integer.valueOf(monthStr,16);//将十六进制转换为十进制
		int day = Integer.valueOf(dayStr,16);//将十六进制转换为十进制
		int hour = Integer.valueOf(hourStr,16);//将十六进制转换为十进制
		int minute = Integer.valueOf(minuteStr,16);//将十六进制转换为十进制
		int second = Integer.valueOf(secondStr,16);//将十六进制转换为十进制
		
		if(year>=0&year<=99&month>=1&month<=12&day>=1&day<=31&hour>=0&hour<=23&minute>=0&minute<=59&second>=0&second<=59){//判断时间范围是否正常
			//以下将年月日时分秒串联起来
			String time = "20"+String.format("%02d", year)+"-"+String.format("%02d", month)+"-"+String.format("%02d", day)+" "+String.format("%02d", hour)+":"+String.format("%02d", minute)+":"+String.format("%02d", second);
			System.out.println("转换成功，显示为："+time);
			return time;
		}else{
			System.out.println("失败！！！Transform的Time的时间格式不符合时间范围，直接存储原字符串，不做任何转换");
			return timeStr;//错误，直接存储原字符串，不做任何转换
		}		
	}
	
	/**
	 * 获取当前系统时间,并且转化为字符串
	 */
	public String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String time = df.format(new Date());
		System.out.println("转换后time："+time);
		return time;
	}
}