package bean;

public class Tb_history_messageBean {
	String vin = null;				//车架号
	String server_time = null;		//服务器时间，插入数据的时间
	String message_time = null;		//报文时间，也就是报文采集时间
	String type = null;				//类型,默认填“国标”
	String checkout = null;			//校验，默认填“成功”
	String message_length = null;	//报文长度
	String original_message = null;	//原始报文（长度1600）

	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
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
}
