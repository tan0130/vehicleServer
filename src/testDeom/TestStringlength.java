package testDeom;

public class TestStringlength {

	public static void main(String[] args) {
		String str3 = "47 46 45 45 46 47 47 49 49 47 46 46 46 47 47 47 46 45 45 45 46 46 46 46 46 46 45 45 44 45 46 46 45 45 47 47 46 45 45 45 46 46 46 47 46 46 46 46 47 47 49 48 47 46 46 46 46 47 47 48 48 47 47 46 46 46 46 46 48 4A 46 46 46 46 46 46 47 46 45 46 46 46 46 46 46 45 45 45 46 46 45 45 46 45 46 45 45 44 46 46 46 46 47 46 47 46 45 46 47 47 47 47 46 45 45 47 46 47 48 48";
		String str = "0C F5 0C F8 0C F8 0C F7 0C F5 0C F4 0C F3 0C F4 0C F5 0C F8 0C F8 0C F7 0C F8 0C F0 0C ED 0C ED 0C EE 0C EB 0C ED 0C EB 0C EE 0C ED 0C EB 0C F5 0C F9 0C F9 0C F9 0C F9 0C F9 0C F4 0C F5 0C F8 0C F8 0C F7 0C F9 0C F9 0C F5 0C F7 0C F8 0C F9 0C F8 0C F7 0C F7 0C F0 0C F0 0C F8 0C F7 0C F9 0C F8 0C F7 0C EE 0C EE 0C ED 0C EE 0C EE 0C F0 0C F0 0C ED 0C EE 0C EE 0C F1 0C F1 0C EE 0C F0 0C F1 0C F1 0C F0 0C F0 0C F1 0C ED 0C F7 0C F8 0C F9 0C F5 0C F9 0C FA 0C F2 0C F4 0C F7 0C F8 0C F9 0C F7 0C F9 0C F5 0C F4 0C F8 0C F7 0C F8 0C F8 0C F8 0C EF 0C F3 0C F8 0C F7 0C F9 0C F8 0C F8 0C F4 0C F4 0C F6 0C F6 0C F6 0C F7 0C F4 0C F6 0C F4 0C F3 0C F4 0C F0 0C CC 0C F0 0C F1 0C F0 0C F0 0C F0 0C F0 0C F0 0C F7 0C F8 0C F5 0C F9 0C F8 0C F7 0C F3 0C F3 0C F8 0C F8 0C F8 0C F8 0C F7 0C F5 0C F7 0C F9 0C F8 0C F8 0C F8 0C F8 0C F2 0C F3 0C F8 0C F8 0C F7 0C F8 0C F8 0C F7 0C F3 0C F0 0C F0 0C EE 0C F0 0C E5 0C EE 0C F0 0C F1 0C EE 0C F8 0C F9 0C F8 0C F9 0C F8 0C FB 0C F2 0C F7 0C F8 0C F7 0C F8 0C F8 0C F5";
		//电压数据
		String str4 ="08 01 01 12 A2 27 26 01 20 00 C9 58 0C F6 0C F5 0C F5 0C F6 0C F6 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F2 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F1 0C F5 0C F6 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F6 0C F7 0C F6 0C F6 0C F6 0C F6 0C F7 0C F6 0C F7 0C F6 0C F7 0C F6 0C F7 0C F6 0C F7 0C F7 0C F7 0C F7 0C F7 0C F6 0C F7 0C F6 0C F7 0C F6 0C F7 0C F6 0C F6 0C F5 0C F6 0C F7 0C F7 0C F7 0C F7 0C F7 0C F7 0C F6 0C F7 0C F6 0C F7 0C F7 0C F6 0C F7 0C F7 0C F6 0C F6";
		String removeBlank = str4.replace(" ", "");
		System.out.println("去除空格后的字符串长度："+removeBlank.length());
		String str2 = "00A8";
		int i = Integer.parseInt(str2,16);
		System.out.println("十六进制转换为十进制："+i);
	}

}
