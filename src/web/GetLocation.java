package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Tb_locationBean;
import database.Tb_location;

public class GetLocation extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetLocation() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// just pusts destory string in log 
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
		Tb_locationBean Bean = new Tb_location().select(vin);//查询最新一条记录
		String gps_status = "\""+Bean.getGps_status()+"\"";		  //GPS定位状态
		String longitude = "\""+Bean.getLongitude()+"\"";			  //经度
		String latitude = "\""+Bean.getLatitude()+"\"";			  //纬度		
		String JSON ="{\"a01\":"+gps_status+",\"a02\":"+longitude+",\"a03\":"+latitude+"}";
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
	}

}
