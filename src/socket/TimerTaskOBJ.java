package socket;

import java.io.IOException;
import java.net.Socket;
import java.util.TimerTask;

public class TimerTaskOBJ extends TimerTask {

	private Socket socket = null;   //
	
	public TimerTaskOBJ(Socket socket){
		this.socket = socket; //从外面获取socket
	}
	@Override
	public void run() {
		try {
			socket.close();
			System.out.println("执行任务：60s之后，服务端没有收到任何数据包，关闭socket");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
