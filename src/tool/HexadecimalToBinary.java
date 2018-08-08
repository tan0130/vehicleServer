package tool;

import java.math.BigDecimal;
import java.math.BigInteger;

public class HexadecimalToBinary {

	public static void main(String[] args) {
		String str =  "F1A9Ha";
		char[] chs = str.toCharArray();
		for (int i = 0; i < chs.length; i++){
			System.out.println(chs[i]+"转化为二进制："+hexadecimalToBinary(chs[i]));
		}
		System.out.println("----------------------去除空格------------------------------");
		String str2 = "A3 23 01 FE 4C 41 39 35 42 37 33 42 58 47 31 4C 43 30 38 30 30 01 00 1E 11 07"
				+ " 18 12 07 11 00 17 38 39 38 36 30 33 31 36 34 33 32 30 32 32 39 30 30 32 36 30 ";
		String str3 = str2.replace(" ", "");
		System.out.println(str3);
		System.out.println("----------------------从字符串中找到第i个字符------------------");
		char a = str3.charAt(0);
		System.out.println(a);
		System.out.println("----------------------从字符串中找到第i到第j个字符-------------");
		String str4 = str3.substring(0,3);//表示从0位置开始往后三个字符
		System.out.println("找到第i到第j个字符："+str4);
		System.out.println("----------------------下面是十六进制转换为十进制数字------------");
		String b = Integer.valueOf("269FB200",16).toString(); 
		System.out.println("转换后："+b);
		System.out.println("----------------------下面是非常大的十六进制转换为十进制数字------------");
		String b2 = new BigInteger("DB9AFE06", 16).toString();
		BigInteger b3 = new BigInteger("DB9AFE06", 16);
		System.out.println("bigint:"+b3);
		System.out.println("转换后："+b2);
		System.out.println("-----------------------下面是纬度位置转换-----------");
		int INT = Integer.valueOf("002220AE",16);
		System.out.println("转换后："+INT);
		double INTdouble = INT;
		double PH = (INTdouble-324000000)/1000; //此处求得INT值
		System.out.println("PH:"+PH);
		int PH1 = (int)(PH/3600);
		double PH2 = (PH%3600)/60;
		// 将double类型转为BigDecimal
		BigDecimal bigDecimal = new BigDecimal(PH2);  
		// 保留两位小数,并且四舍五入
		double PH2Double = bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println("PH1:"+PH1+",PH2:"+PH2Double);
		System.out.println("-----------------------下面是经度位置转换-----------");
		INT = Integer.valueOf("1371F9AE",16);
		System.out.println("转换后："+INT);
		INTdouble = INT;
		PH = (INTdouble-648000000)/1000; //此处求得INT值
		System.out.println("PH:"+PH);
		PH1 = (int)(PH/3600);
		PH2 = (PH%3600)/60;
		// 将double类型转为BigDecimal
		bigDecimal = new BigDecimal(PH2);  
		// 保留两位小数,并且四舍五入
		PH2Double = bigDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println("PH1:"+PH1+",PH2:"+PH2Double);
		System.out.println("----------------------将数字补0显示---------------------");
		int c = 11;
        System.out.println(String.format("%02d", c));
        System.out.println("----------------------将字符串转换为int-------------------");
        String str5 = "123";
        try {
            int d = Integer.parseInt(str5);
            System.out.println("123字符转换:"+d);
        } catch (NumberFormatException s) {
            s.printStackTrace();
        }
        System.out.println("---------------------求字符串的长度------------------");
        String s = "asdfghjkl";
        int length = s.length();
        System.out.println("字符串的长度是:"+length);
	}
	
	
	/**
	 * 根据字符将该字符转换为对应的二进制含义,用String类型返回
	 * @param ch
	 * @return
	 */
	public static String hexadecimalToBinary(char ch){
		switch (ch) {
		case '0':
			//System.out.println(ch);
		   return "0000";
		case '1':
			//System.out.println(ch);
			return "0001";
		case '2':
			//System.out.println(ch);
			return "0010";
		case '3':
			//System.out.println(ch);
			return "0011";
		case '4':
			//System.out.println(ch);
			return "0100";
		case '5':
			//System.out.println(ch);
			return "0101";
		case '6':
			//System.out.println(ch);
			return "0110";
		case '7':
			//System.out.println(ch);
			return "0111";
		case '8':
			//System.out.println(ch);
			return "1000";
		case '9':
			//System.out.println(ch);
			return "1001";
		case 'A':
			//System.out.println(ch);
			return "1010";
		case 'a':
			//System.out.println(ch);
			return "1010";
		case 'B':
			//System.out.println(ch);
			return "1011";
		case 'b':
			//System.out.println(ch);
			return "1011";
		case 'C':
			//System.out.println(ch);
			return "1100";
		case 'c':
			//System.out.println(ch);
			return "1100";
		case 'D':
			//System.out.println(ch);
			return "1101";
		case 'd':
			//System.out.println(ch);
			return "1101";
		case 'E':
			//System.out.println(ch);
			return "1110";
		case 'e':
			//System.out.println(ch);
			return "1110";
		case 'F':
			//System.out.println(ch);
			return "1111";		
		case 'f':
			//System.out.println(ch);
			return "1111";		
		default:
		    System.out.println("出错：该十六进制找不到对应的二进制");
		    return null;
		}
		
		/**
		 * 去除字符串中的空格
		 */
		
		
	}

}
