package testDeom;

import bean.Tb_login_outBean;
import database.Tb_login_out;

public class Tb_login_outTest {

	public static void main(String[] args) {
		Tb_login_out ta = new Tb_login_out();
		
		String loginData = "23 23 01 FE 4C 41 39 35 42 37 33 42 58 47 31 4C 43 30 38 30 30 01 00 1E 11"
				+ " 07 18 12 07 11 00 17 38 39 38 36 30 33 31 36 34 33 32 30 32 32 39 30 30 32 36 30 01 00 D3  ";
		String currentLogout = "23 23 04 FE 4C 41 39 35 42 37 33 42 58 47 31 4C 43 30 38 30 30 01 00 08 11 07 18 0F 36 0E 00 10 F6 ";
//		ta.analysis(currentLogout);
//		ta.insert();
//		Tb_login_outBean LOBean = ta.select("LA95B73BXG1LC0800");
		String state = ta.select2("LA95B73BXG1LC0800");
		System.out.println("登录状态是："+state);
//		System.out.println("测试：打印登录时间时间："+LOBean.getLogin_time());
//		System.out.println("测试：打印登出时间时间："+LOBean.getLogout_time());
//		System.out.println("tb_login_out执行完毕");
	}
}
