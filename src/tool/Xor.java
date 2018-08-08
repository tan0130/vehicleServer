package tool;


/**
 * 该类完成BCC异或校验
 * @author zuck zhao
 *
 */
public class Xor {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "01 FE 4C 41 39 35 42 37 33 42 58 47 31 4C 43 30 38 30 30 01 00 1E 11 07 18 12 07 11 00 17 38 39 38 36 30 33 31 36 34 33 32 30 32 32 39 30 30 32 36 30 01 00";
		String str3 = "01 A0 7C FF 02";
		String removeBlank = str3.replace(" ", "");
		System.out.println("去除空格后字符串："+removeBlank);
		byte[] b = hexStringToByteArray(removeBlank);//字符串转换为字节数组
		byte Xor = getXor(b);  
		String bytestr = byteTo16(Xor);
		System.out.println("校验码为："+bytestr);//打印异或校验字符串
	}
	
	
	/**
	 * 目的：将输入的字节数组进行异或校验
	 * @param datas
	 * @return
	 */
	public static byte getXor(byte[] datas){
		 
		byte temp=datas[0];
			
		for (int i = 1; i <datas.length; i++) {
			temp ^=datas[i];
		}
	 
		return temp;
	}
	
	/**
	 * 将字节数组拼接成字符串数组
	 * bt:传入字节数组
	 * x：有效字节长度
	 */
	
	public static String byteTo16Array(byte[] bt,int x){
		String str = "";
		for(int i = 0; i<x; i++){
			str = str+byteTo16(bt[i]);
		}
		System.out.println("重新拼接字符位     ："+str);
		return str;
		
	}
	/**
	 * 将单个字节里面的两个十六进制值转换为十六进制字符，并且拼接起来
	 * @param bt
	 * @return
	 */
	public static String byteTo16(byte bt){
	    String[] strHex={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	    String resStr="";
	    int low =(bt & 15);
	    int high = bt>>4 & 15;
	    resStr = strHex[high]+strHex[low];
	    return resStr;
	}
	
	/**
	 * 16进制表示的字符串转换为字节数组
	 *
	 * @param s 16进制表示的字符串
	 * @return byte[] 字节数组
	 */
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] b = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
	        b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
	                .digit(s.charAt(i + 1), 16));
	    }
	    return b;
	}

}
