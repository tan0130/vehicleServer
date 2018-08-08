package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Tb_history_message;

public class GetHistoryMessage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetHistoryMessage() {
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
        String starttime = request.getParameter("starttime"); 
        String endtime = request.getParameter("endtime"); 
//		String vin = "\""+Bean.getVin()+"\"";				//车架号
//	    String server_time = "\""+Bean.getServer_time()+"\"";		//服务器时间，插入数据的时间
//		String message_time = "\""+Bean.getMessage_time()+"\"";		//报文时间，也就是报文采集时间
//	    String type = "\""+"国标"+"\"";				//类型,默认填“国标”
//	    String checkout = "\""+"成功"+"\"";			//校验，默认填“成功”
//		String message_length = "\""+Bean.getMessage_length()+"\"";	//报文长度
//		String original_message = "\""+Bean.getOriginal_message()+"\"";	//原始报文（长度1600）		
//		String JSON ="{\"vin\":"+vin+",\"servertime\":"+server_time+",\"codetime\":"+message_time+",\"codestyle\":"+type+",\"codecheck\":"+checkout+",\"codelength\":"+message_length+",\"originalcode\":"+original_message+"}";
		
        String JSON = new Tb_history_message().select(vin, starttime, endtime);
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
