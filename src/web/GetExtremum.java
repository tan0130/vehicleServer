package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tb_extremumBean;
import database.Tb_extremum;

public class GetExtremum extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetExtremum() {
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
        String vin = request.getParameter("vin"); 
		PrintWriter out = response.getWriter();
		Tb_extremumBean Bean = new Tb_extremum().select(vin);//查询最新一条记录
		String maxvol_package_number1 = "\""+Bean.getMaxvol_package_number1()+"\"";   //单体电压最高值包序号
		String maxvol_monomer_number2 = "\""+Bean.getMaxvol_monomer_number2()+"\"";   //单体电压最高值单体序号
		String max_voltage3 = "\""+Bean.getMax_voltage3()+"\"";			    //单体电压最高值
		String minvol_package_number4= "\""+Bean.getMinvol_package_number4()+"\"";    //单体电压最低值包序号
		String minvol_monomer_number5 = "\""+Bean.getMinvol_monomer_number5()+"\"";   //单体电压最低值单体序号
		String min_voltage6 = "\""+Bean.getMin_voltage6()+"\"";			    //单体电压最低值
		String maxtemp_package_number7 = "\""+Bean.getMaxtemp_package_number7()+"\"";  //最高温度值包序号
		String maxtemp_monomer_number8 = "\""+Bean.getMaxtemp_monomer_number8()+"\"";  //最高温度值单体序号
		String max_temperature9 = "\""+Bean.getMax_temperature9()+"\"";		    //最高温度值
		String mintemp_package_number10 = "\""+Bean.getMintemp_package_number10()+"\""; //最低温度值包序号
		String mintemp_monomer_number11 = "\""+Bean.getMintemp_monomer_number11()+"\""; //最低温度值单体序号
		String min_temperature12 = "\""+Bean.getMin_temperature12()+"\"";	    //最低温度值
		String JSON ="{\"e01\":"+maxvol_package_number1+",\"e02\":"+maxvol_monomer_number2+",\"e03\":"+max_voltage3+",\"e04\":"+minvol_package_number4+",\"e05\":"+minvol_monomer_number5+",\"e06\":"+min_voltage6+",\"e07\":"+maxtemp_package_number7+",\"e08\":"+maxtemp_monomer_number8+",\"e09\":"+max_temperature9+",\"e10\":"+mintemp_package_number10+",\"e11\":"+mintemp_monomer_number11+",\"e12\":"+min_temperature12+"}";
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
