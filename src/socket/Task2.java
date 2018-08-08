package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Timer;

import database.DoAll;

	/**
	 * 
	 * 处理Socket请求的线程类
	 */
class Task2 implements Runnable {
 
		private Socket socket = null;
		OutputStream out = null;
		InputStream  in =  null ;
		Timer timer = new Timer();;  //设置一个计时器，用来执行一个在60s以后关闭socket的任务
		TimerTaskOBJ timerTask = new TimerTaskOBJ(socket);//将该socket传入定时任务中去。
		
		
		/**
		 * 构造函数
		 */
		public Task2(Socket socket) {
			this.socket = socket;
		}
 
		@Override
		public void run() {
			
			int j = 0; //看socket分析的次数
			try {
				in = socket.getInputStream();
				out = socket.getOutputStream();
				while( socket.isClosed()==false){//如果socket没有被关闭，那么则可以一直执行  
					if(in.available()!=0){
						handlerSocket();
					}
					j++;
					System.out.println("socket分析的次数:"+j);
					Thread.sleep(1*1000);
				}
				System.out.println("Task2的run方法执行完毕，socket已经关闭");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
 
		/**
		 * 跟客户端Socket进行通信
		 * 
		 * @throws IOException
		 */
		private void handlerSocket() throws Exception {
			// 跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了
			/**
			 * 在从Socket的InputStream中接收数据时，像上面那样一点点的读就太复杂了，
			 * 有时候我们就会换成使用BufferedReader来一次读一行
			 * 
			 * BufferedReader的readLine方法是一次读一行的，这个方法是阻塞的，直到它读到了一行数据为止程序才会继续往下执行，
			 * 那么readLine什么时候才会读到一行呢？直到程序遇到了换行符或者是对应流的结束符readLine方法才会认为读到了一行，
			 * 才会结束其阻塞，让程序继续往下执行。
			 * 所以我们在使用BufferedReader的readLine读取数据的时候一定要记得在对应的输出流里面一定要写入换行符（
			 * 流结束之后会自动标记为结束，readLine可以识别），写入换行符之后一定记得如果输出流不是马上关闭的情况下记得flush一下，
			 * 这样数据才会真正的从缓冲区里面写入。
			 */

		       byte[] getBytes = new byte[2048];      //缓存字节数组，用于接收客户端字节数据流
		       
		       
		       /**
		        * 判读inputstream是否还可以读取，返回0表示不可读取，那么置0
		        */
		       if(in.available()==0){
		    	   System.out.println("inputstream已经读取完毕，该轮不做数据处理");
		    	   return;
		       }
		       
		       int x = in.read(getBytes);  //将数据流写入到缓存字节数组b中，返回有效数据长度
		       if(x == -1){
		    	   return;
		       }
		       System.out.println("客户端传入字节数组长度："+x);
		       
		       /**
		        * 以下是打印获取的字节数组
		        */
		       String str = "";
		       str = byteTo16Array(getBytes,x); //将16进制值的字节数组拼接成16进制字符,x表示取getBytes里面的有效字节数量
		       System.out.println("16进制字节数组转换为16进制字符串之后打印："+str);
		       
		       byte minglingbiaoshi = getBytes[2]; //获取命令标识字节，用于判断登入登出以及实时信息
		       System.out.println("Server程序得到的命令标识为:"+byteTo16(minglingbiaoshi));
		       /**
		        * 进行数据处理
		        */
		       DoAll doAll = new DoAll();//设置数据处理对象，包括登入、登出、实时、补发
		       String strArray = byteTo16Array(getBytes ,x);  //将字节数组转换为字符串

		       /**
		        * 设置返回字节数组
		        */
		       byte[] returnBytes = new byte[x];
		       for(int j= 0;j< x;j++){ //将获取到数组传值给返回数组
		    	   returnBytes[j]= getBytes[j];
		       }
		       
		       if(minglingbiaoshi == 0x01){//为登录报文
		    	   /**
			        * 以下是获取校验数组，并进行校验
			        */
			      
			       byte loginJiaoyan = getXor(getBytes,2,x-2);    //此处是用于登入校验,x-2表示一直到到最第二个字节
			       str = byteTo16(loginJiaoyan);
			       System.out.println("服务端分析所得到的16进制校验码打印："+str);
			       System.out.println("客户端传入数据最后一个字节校验码16进制打印："+byteTo16(getBytes[x-1]));
			       
			       if(loginJiaoyan == getBytes[x-1]){//服务端校验结果和客户端自带数据校验码一致，因此信息正确
			    	   try {
							doAll.insert(strArray);
						} catch (StringIndexOutOfBoundsException e) {
							e.printStackTrace();
						}
			    	   System.out.println("登入校验结果一致，进行数据处理，保存到数据库");
			    	   returnBytes[3] = 0x01;  //更改应答标识，设置为成功
			       }else{
			    	   System.out.println("登入校验结果不一致，不进行数据处理，不保存到数据库");
			    	   returnBytes[3] = 0x02;  //更改应答标识，设置为成功
			       }
			       out.write(returnBytes);   //写入返回包
		       }else if(minglingbiaoshi == 0x04){//以下为登出报文，暂时不做处理
		    	   /**
			        * 以下是获取校验数组，并进行校验
			        */
			      
			       byte loginJiaoyan = getXor(getBytes,2,x-2);    //此处是用于登出校验,x-2表示一直到到最第二个字节
			       str = byteTo16(loginJiaoyan);
			       System.out.println("服务端分析所得到的16进制校验码打印："+str);
			       System.out.println("客户端传入数据最后一个字节校验码16进制打印："+byteTo16(getBytes[x-1]));
			       
			       if(loginJiaoyan == getBytes[x-1]){//服务端校验结果和客户端自带数据校验码一致，因此信息正确
			    	   try {
							doAll.insert(strArray);
						} catch (StringIndexOutOfBoundsException e) {
							e.printStackTrace();
						}
			    	   System.out.println("登出校验结果一致，进行数据处理，保存到数据库");
			    	   returnBytes[3] = 0x01;  //更改应答标识，设置为成功
			       }else{
			    	   System.out.println("登出校验结果不一致，不进行数据处理，不保存到数据库");
			    	   returnBytes[3] = 0x02;  //更改应答标识，设置为成功
			       }
			       out.write(returnBytes);   //写入返回包
			       socket.close();   //登出完之后，立即执行关闭socket的操作
			       System.out.println("登出操作，立即执行关闭socket");
			       return; //终止该方法的执行
		       }else if(minglingbiaoshi == 0x07){//以下为心跳包
		    	   out.write(returnBytes);
		       }else{//以下为实时或补发报文
		    	   try {
						doAll.insert(strArray);
					} catch (StringIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
		    	   System.out.println("为实时或补发，不做任何返回！");
		       }
		       
		       /**
		        * 如果服务服务端执行了一次数据分析，也即是获得了一个数据包，无论是登入登出还是实时、补发、心跳，则关闭上次的计时任务，再次执行一个新的计时任务
		        * 任务：关闭socket
		        * 定时时间：60s
		        */
		       timerTask.cancel();//关闭上次任务
		       timerTask = new TimerTaskOBJ(socket); //再次将socket传入，设置一个新的任务
		       timer.schedule(timerTask, 60*1000);  //延迟六十秒后，执行新的任务，任务为：关闭socket

		}
		
		
		
		
	
		/**
		 * 将字节数组拼接成字符串数组
		 * bt:传入字节数组
		 * x：有效字节长度
		 */
		
		public static String byteTo16Array(byte[] bt,int x){
			String str = "";
			for(int i = 0; i<x; i++){
				str = str+byteTo16(bt[i]);
			}
			System.out.println("重新拼接字符位     ："+str);
			return str;
			
		}
		/**
		 * 将单个字节里面的两个十六进制值转换为十六进制字符，并且拼接起来
		 * @param bt
		 * @return
		 */
		public static String byteTo16(byte bt){
		    String[] strHex={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		    String resStr="";
		    int low =(bt & 15);
		    int high = bt>>4 & 15;
		    resStr = strHex[high]+strHex[low];
		    return resStr;
		}
		
		/**
		 * 16进制表示的字符串转换为字节数组
		 *
		 * @param s 16进制表示的字符串
		 * @return byte[] 字节数组
		 */
		public static byte[] hexStringToByteArray(String s) {
		    int len = s.length();
		    byte[] b = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
		        b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
		                .digit(s.charAt(i + 1), 16));
		    }
		    return b;
		}
		
		/**
		 * 目的：将输入的字节数组进行异或校验
		 * @param datas
		 * @return
		 */
		public static byte getXor(byte[] datas){
			 
			byte temp=datas[0];
			
			for (int i = 1; i <datas.length; i++) {
				temp ^=datas[i];
			}
		 
			return temp;
		}
		
		/**
		 * 目的：将输入的字节数组的第x个字节到第y个字节进行异或校验
		 * @param datas
		 * @return
		 */
		public static byte getXor(byte[] datas,int low,int high){
			 
			byte temp=datas[low];
				
			for (int i = low+1; i <=high ; i++) {
				temp ^=datas[i];
			}
		 
			return temp;
		}
 
}