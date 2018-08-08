package socket2;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
/**
 * 保持池中的socket一直存活 每隔一段时间遍历池检查socket对象 如果已连接那么发送心跳包 如果已断开 那么重新建立连接
  * @ClassName: DaemonThread
  * @Description: TODO
  * @Copyright: Copyright (c) 2016 
  * @Company: 深圳市梦网科技股份有限公司
  * @author hxq
  * @date 2016-8-1 上午10:26:43
  * @version V1.0
 */
public class DaemonThread implements Runnable{
    private SocketPool pool;
    public DaemonThread(SocketPool pool){
        this.pool=pool;
    }
    public void run() {
        List<SocketMember> container=pool.getSocketContainer();
        while(true){
            //10秒检查一次    
            try {
                Thread.sleep(10000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            //遍历检查是否连接 
            for(int i=0;i<container.size();i++){
                SocketMember member=container.get(i);
                Socket socket=member.getSocket();
                //给此socket加锁
                synchronized (socket) {
                    if(!member.isInUse()){
                        
                        if(socket.isConnected()){
                            //如果连接发送心跳包
                            KeepAliveExcutor excutor=new KeepAliveExcutor(member);
                            excutor.request();
                            
                        }else{
                            //如果失败重新建立socket
                            try {
                                socket=new Socket(pool.getHost(), pool.getPort());
                                member.setSocket(socket);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println(socket.getLocalPort()+" 断线重连");
                        }
                    }
                }
                //System.out.println(socket.getLocalPort()+" 状态"+socket.isConnected());  
            }
        }
        
    }

}
/**
 * 发送心跳包的executor
  * @ClassName: KeepAliveExcutor
  * @Description: TODO
  * @Copyright: Copyright (c) 2016 
  * @Company: 深圳市梦网科技股份有限公司
  * @author hxq
  * @date 2016-8-16 下午02:16:32
  * @version V1.0
 */
class KeepAliveExcutor extends AbstractSocketExecutor{

    public KeepAliveExcutor(SocketMember socketMember) {
        super(socketMember);
    }
    
    public void request(){ 
        String request=SocketPoolUtil.getNowTimestamp();
        super.request(request, "utf-8");
    }
    
}


