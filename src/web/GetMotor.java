package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tb_electric_motorBean;
import database.Tb_electric_motor;

public class GetMotor extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetMotor() {
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
		Tb_electric_motorBean Bean = new Tb_electric_motor().select(vin);//查询最新一条记录
		String message_time = "\""+Bean.getMessage_time()+"\"";		//报文时间
		String validity = "\""+"有效"+"\"";				//数据有效性(暂时置为1)
		String amount = "\""+Bean.getAmount()+"\"";				//驱动电机个数
		String kong = "\""+"-"+"\"";    //空格
		String order_1_1 = "\""+Bean.getOrder_1_1()+"\"";			//电机序号1
		String status_1_2 = "\""+Bean.getStatus_1_2()+"\"";			//电机状态1
		String controller_temp_1_3 = "\""+Bean.getController_temp_1_3()+"\"";	//电机控制器温度1
		String rotate_speed_1_4 = "\""+Bean.getRotate_speed_1_4()+"\"";		//电机转速1
		String torque_1_5 = "\""+Bean.getTorque_1_5()+"\"";			//电机转矩1
		String temperature_1_6 = "\""+Bean.getTemperature_1_6()+"\"";		//电机温度1
		String voltage_1_7 = "\""+Bean.getVoltage_1_7()+"\"";			//电机电压1
		String electricity_1_8 = "\""+Bean.getElectricity_1_8()+"\"";		//电机母线电流1
		String order_2_1 = "\""+Bean.getOrder_2_1()+"\"";			//电机序号2
		String status_2_2 = "\""+Bean.getStatus_2_2()+"\"";			//电机状态2
		String controller_temp_2_3 = "\""+Bean.getController_temp_2_3()+"\"";	//电机控制器温度2
		String rotate_speed_2_4 = "\""+Bean.getRotate_speed_2_4()+"\"";		//电机转速2
		String torque_2_5 = "\""+Bean.getTorque_2_5()+"\"";			//电机转矩2
		String temperature_2_6 = "\""+Bean.getTemperature_2_6()+"\"";		//电机温度2
		String voltage_2_7 = "\""+Bean.getVoltage_2_7()+"\"";			//电机电压2
		String electricity_2_8 = "\""+Bean.getElectricity_2_8()+"\"";		//电机母线电流2
		String JSON ="{\"m01\":"+message_time+",\"m02\":"+validity+",\"m03\":"+amount+",\"m04\":"+kong+",\"m05\":"+order_1_1+",\"m06\":"+status_1_2+",\"m07\":"+controller_temp_1_3+",\"m08\":"+rotate_speed_1_4+",\"m09\":"+torque_1_5+",\"m10\":"+temperature_1_6+",\"m11\":"+voltage_1_7+",\"m12\":"+electricity_1_8+",\"m13\":"+order_2_1+",\"m14\":"+status_2_2+",\"m15\":"+controller_temp_2_3+",\"m16\":"+rotate_speed_2_4+",\"m17\":"+torque_2_5+",\"m18\":"+temperature_2_6+",\"m19\":"+voltage_2_7+",\"m20\":"+electricity_2_8+"}";
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
