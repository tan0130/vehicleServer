package testDeom;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NowString {

	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String time = df.format(new Date());
		System.out.println("转换后time："+time);
		
		/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time);  
        String string = simpleDateFormat.format(date);  
        return string; */
		
	}

}
