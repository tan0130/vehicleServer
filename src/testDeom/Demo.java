package testDeom;

import database.GetResultSet;

public class Demo {

	public static void main(String[] args) {
		GetResultSet Get = new GetResultSet();
		Get.select();
		System.out.println("执行完毕！");
	}

}
