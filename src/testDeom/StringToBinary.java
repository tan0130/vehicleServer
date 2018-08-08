package testDeom;

public class StringToBinary {

	public static void main(String[] args) {
		//比如我现在要转这个字符串
		String Str = new String("abcd");
		//先把他变为字符数组
		char[] chs = Str.toCharArray();
		//然后通过integer中的toBinaryString方法来一个一个转
		for (int i = 0; i < chs.length; i++){
			System.out.print("  "+chs[i]);

		}
		System.out.println("");

		System.out.println("换行");
		for (int i = 0; i < chs.length; i++) {
			System.out.print(Integer.toBinaryString(chs[i]));
		}	
		
	}

}
