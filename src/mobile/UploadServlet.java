package mobile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Tb_alert;
import database.Tb_electric_motor;
import database.Tb_extremum;
import database.Tb_history_message;
import database.Tb_location;
import database.Tb_login_out;
import database.Tb_vehicle;

public class UploadServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		PrintWriter out = response.getWriter();
		String data=request.getParameter("value");//从客户获取数据
		String dataRemoveBlank = data.replace(" ", "");//将获取的字符串去除掉空格
		String minglingbiaozhi = dataRemoveBlank.substring(4,6);//获取命令标识十六进制，用于判断登入登出、实时信息、补发信息
		String minglingbiaozhiInt= Integer.valueOf(minglingbiaozhi,16).toString();//转十进制字符串
		if(minglingbiaozhiInt.equals("02") || minglingbiaozhiInt.equals("03") ){//实时信息或补发信息
			if(minglingbiaozhiInt.equals("02")){
				System.out.println("命令标识为02，为实时信息上报");
			}else{
				System.out.println("命令标识为03，为补发信息上报");
			}
			Tb_vehicle Tb_vehicle = new Tb_vehicle();//执行整车信息
			Tb_vehicle.analysis(dataRemoveBlank);
			System.out.println("Tb_vehicleTest执行完毕");
			
			Tb_electric_motor tb_electric_motor = new Tb_electric_motor();//执行驱动电机
			tb_electric_motor.analysis(dataRemoveBlank);
			System.out.println("Tb_electric_motor执行完毕");
			
			Tb_location tb_location = new Tb_location();//执行位置信息
			tb_location.analysis(dataRemoveBlank);
			System.out.println("Tb_location执行完毕");
			
			Tb_extremum tb_extremum = new Tb_extremum();//执行极值数据
			tb_extremum.analysis(dataRemoveBlank);
			
			Tb_alert tb_alert = new Tb_alert();//执行报警数据
			tb_alert.analysis(dataRemoveBlank);
			System.out.println("Tb_alert执行完毕");
			
			Tb_history_message tb_history_message = new Tb_history_message();//执行历史报文
			tb_history_message.analysis(dataRemoveBlank);
			System.out.println("Tb_history_message执行完毕");
			
			if(minglingbiaozhiInt.equals("02")){
				System.out.println("实时信息处理完毕！！！");
			}else{
				System.out.println("补发信息处理完毕！！！");
			}
			out.println();
		}else if(minglingbiaozhiInt.equals("01") || minglingbiaozhiInt.equals("04")){//车辆登入或登出
			Tb_login_out tb_login_out = new Tb_login_out();
			tb_login_out.analysis(dataRemoveBlank);
			tb_login_out.insert();
			System.out.println("tb_login_out执行完毕");
		}
		out.flush();
		out.close();//
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
