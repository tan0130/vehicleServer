package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tb_login_outBean;
import database.Tb_login_out;

public class GetStatus extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetStatus() {
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
		//response.setContentType("text/json; charset=utf-8");
		response.setContentType("application/json;charset=UTF-8");
        String vin = request.getParameter("vin"); 
		PrintWriter out = response.getWriter();
		Tb_login_outBean Bean = new Tb_login_out().select(vin);//查询最新一条记录
		String login_serial_number = "\""+Bean.getSerial_number()+"\"";//获取登入流水号
		String logout_serial_number = "\""+Bean.getSerial_number()+"\"";//获取登出流水号
		String login_time = "\""+Bean.getLogin_time()+"\"";//获取登出时间
		String logout_time = "\""+Bean.getLogout_time()+"\"";//获取登出时间
		String iccid = "\""+Bean.getIccid()+"\"";//获取iccid
		String JSON ="{\"incode\":"+login_serial_number+",\"outcode\":"+logout_serial_number+",\"intime\":"+login_time+",\"outtime\":"+logout_time+",\"iccid\":"+iccid+"}";
		String JSONP="jsonpCallback("+JSON+")";
		out.println(JSONP);
		out.flush();
		out.close();
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
