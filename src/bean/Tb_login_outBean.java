package bean;

/**
 * Tb_login_outBean的bean模型
 * @author zuck zhao
 *
 */
public class Tb_login_outBean {
	
	String vin = null;
	String serial_number = null; //登入登出流水号
	String login_time = null;
	String logout_time = null;
	String iccid = null;
	
	String server_time = null;		//服务器时间，插入数据的时间
    String message_time = null;		//报文时间，也就是报文采集时间
    String type = null;				//类型,默认填“国标”
    String checkout = null;			//校验，默认填“成功”
	String message_length = null;	//报文长度
	String original_message = null;	//原始报文（长度1600）
	
	public String getVin() {
		return vin;
	}
	public String getServer_time() {
		return server_time;
	}
	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}
	public String getMessage_time() {
		return message_time;
	}
	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getMessage_length() {
		return message_length;
	}
	public void setMessage_length(String message_length) {
		this.message_length = message_length;
	}
	public String getOriginal_message() {
		return original_message;
	}
	public void setOriginal_message(String original_message) {
		this.original_message = original_message;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public String getLogout_time() {
		return logout_time;
	}
	public void setLogout_time(String logout_time) {
		this.logout_time = logout_time;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	
	
}
