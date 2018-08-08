package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tb_vehicleBean;
import database.Tb_vehicle;

public class GetVehicle extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetVehicle() {
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
		PrintWriter out = response.getWriter();
        String vin = request.getParameter("vin"); 
		Tb_vehicleBean Bean = new Tb_vehicle().select(vin);		//查询最新一条记录
		String ve_status1 = "\""+Bean.getVe_status1()+"\"";				//车辆状态
		String charge_status2 = "\""+Bean.getCharge_status2()+"\"";		//充电状态
		String operation_pattern3 = "\""+Bean.getOperation_pattern3()+"\"";//运行模式
		String ve_speed4 = "\""+Bean.getVe_speed4()+"\"";					//车速
		String mileage5 = "\""+Bean.getMileage5()+"\"";					//里程
		String total_voltage6 = "\""+Bean.getTotal_voltage6()+"\"";		//总电压
		String total_electricity7 = "\""+Bean.getTotal_electricity7()+"\"";//总电流
		String soc8 = "\""+Bean.getSoc8()+"\"";							//SOC
		String dcdc_status9 = "\""+Bean.getDcdc_status9()+"\"";			//DC-DC状态
		String grade10 = "\""+Bean.getGrade10()+"\"";						//档位
		String resistance11 = "\""+Bean.getResistance11()+"\"";			//正极对地电阻
		String accelerate_length12 = "\""+Bean.getAccelerate_length12()+"\"";//加速踏板行程值
		String brake_length13 = "\""+Bean.getBrake_length13()+"\"";		//制动踏板行程值
		String drive_status14 = "\""+Bean.getDrive_status14()+"\"";		//驱动状态
		String brake_status15 = "\""+Bean.getBrake_status15()+"\"";		//制动状态
		String JSON ="{\"v01\":"+ve_speed4+",\"v02\":"+brake_length13+",\"v03\":"+mileage5+",\"v04\":"+accelerate_length12+",\"v05\":"+grade10+",\"v06\":"+operation_pattern3+",\"v07\":"+brake_status15+",\"v08\":"+drive_status14+",\"v09\":"+dcdc_status9+",\"v10\":"+ve_status1+",\"v11\":"+total_voltage6+",\"v12\":"+total_electricity7+",\"v13\":"+soc8+",\"v14\":"+resistance11+",\"v15\":"+charge_status2+"}";
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
