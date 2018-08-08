package bean;

public class Tb_locationBean {
	String gps_status = null;			//GPS定位状态
	String longitude = null;			//经度
	String latitude = null;				//纬度
	
	public String getGps_status() {
		return gps_status;
	}
	public void setGps_status(String gps_status) {
		this.gps_status = gps_status;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}
