package testDeom;

/**
 * 该函数用于测试登入数据包的位置是否正确
 * 1.该函数变量均采用拼音命名
 * @author zuck zhao
 *
 */
public class LoginDataTest {

	public static void main(String[] args) {
		String loginData = "23 23 01 FE 4C 41 39 35 42 37 33 42 58 47 31 4C 43 30 38 30 30 01 00 1E 11"
				+ " 07 18 12 07 11 00 17 38 39 38 36 30 33 31 36 34 33 32 30 32 32 39 30 30 32 36 30 01 00 D3  ";
		String loginDataRemoveBlank = loginData.replace(" ", "");
		System.out.println(loginDataRemoveBlank);
		System.out.println("-----------------以下是获取的各个数据项---------------");
		String minglingbiaozhi = loginDataRemoveBlank.substring(4,6);
		String yingdabiaozhi = loginDataRemoveBlank.substring(6, 8);
		String weiyishibiema = loginDataRemoveBlank.substring(8, 42);
		String jiamifangshi = loginDataRemoveBlank.substring(42, 44);
		String danyuanchangdu = loginDataRemoveBlank.substring(44, 48);
		String shujucaijishijian = loginDataRemoveBlank.substring(48, 60);
		String dengruliushuihao = loginDataRemoveBlank.substring(60, 64);
		String iccid = loginDataRemoveBlank.substring(64, 104);
		System.out.println("命令标识："+minglingbiaozhi);
		System.out.println("应答标识："+yingdabiaozhi);
		System.out.println("唯一识别码："+weiyishibiema);
		System.out.println("加密方式："+jiamifangshi);
		System.out.println("单元长度："+danyuanchangdu);
		System.out.println("数据采集时间："+shujucaijishijian);
		System.out.println("登入流水号："+dengruliushuihao);
		System.out.println("iccid："+iccid);

	}

}
