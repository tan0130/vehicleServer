package socket2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import socket.Server.Task;

/**
 * SocketServer.java Create on 2017年12月16日 下午11:42:03    
 * 类功能说明:   socket服务端
 * @Author coshaho
 */
public class SocketServer 
{
    public static void main(String[] args) throws Exception 
    {// 为了简单起见，所有的异常信息都往外抛
		int port = 5050;
		// 定义一个ServiceSocket监听在端口5050上
		ServerSocket server = new ServerSocket(port);
		System.out.println("等待与客户端建立连接...");
		int i = 0;
		while (true) {
			i++;
			// server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
			Socket client = server.accept();
			/**
			 * 我们的服务端处理客户端的连接请求是同步进行的， 每次接收到来自客户端的连接请求后，
			 * 都要先跟当前的客户端通信完之后才能再处理下一个连接请求。 这在并发比较多的情况下会严重影响程序的性能，
			 * 为此，我们可以把它改为如下这种异步处理与客户端通信的方式
			 */
			// 每接收到一个Socket就建立一个新的线程来处理它
			//new Thread(new Task(socket)).start();
			 // 获取InputStream读取数据
	        InputStream in = client.getInputStream();
	        byte[] b = new byte[1024];
	        // 客户端关闭输出流后服务端会读取到-1标志
	        while(-1 != in.read(b))
	        {
	            System.out.println(new String(b));
	        }
	       
	        // 获取OutputStream输出数据
	        OutputStream out = client.getOutputStream();
	        out.write("hello, client".getBytes());
	        // 输出结束，关闭输出流
	        client.shutdownOutput();
	        System.out.println("第"+i+"次连接");
		}
       // Socket client = server.accept();
        
       
       
        System.out.println("Server close. " + System.currentTimeMillis());
        server.close();
    }
}