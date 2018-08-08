package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socket.Server;
import bean.Tb_alertBean;
import database.Tb_alert;

public class GetAlert extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("执行GetAlert的init(ServletConfig config)");
		new Thread(new Server()).start();
		System.out.println("在tomcat启动时开启了serversocket服务");
		
	}

	/**
	 * Constructor of the object.
	 */
	public GetAlert() {
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
		Tb_alertBean Bean = new Tb_alert().select(vin);//查询最新一条记录
		String temperature_difference0 = Bean.getTemperature_difference0();	//0温度差异报警
		String temperature_difference02 = "\""+temperature_difference0+"\"";
		String JSON ="{\"a01\":"+temperature_difference02+",\"a02\":\"正常\",\"a03\":\"正常\",\"a04\":\"正常\",\"a05\":\"正常\",\"a06\":\"正常\",\"a07\":\"正常\",\"a08\":\"正常\",\"a09\":\"正常\",\"a10\":\"正常\",\"a11\":\"正常\",\"a12\":\"正常\",\"a13\":\"正常\",\"a14\":\"正常\",\"a15\":\"正常\",\"a16\":\"正常\",\"a17\":\"正常\",\"a18\":\"正常\",\"a19\":\"正常\"}";
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
	 * 目的：在tomcat启动时候加载socket服务
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		System.out.println("执行GetAlert的init()");
	}

}
