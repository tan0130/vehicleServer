package socket2;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketPool {
//host
private String host;
//port
private int port;
//初始化socket数
private int initSize=5;
//最大socket数
private int maxSize=5;
//socket对象容器
private List<SocketMember> socketContainer=new ArrayList<SocketMember>(initSize);

public SocketPool(String host,int port){
    this.host=host;
    this.port=port;
    buildPoolPart();
}
//给socket容器增加成员 一次增加initSize个成员
private List<SocketMember> buildPoolPart(){
    List<SocketMember> poolPart=new ArrayList<SocketMember>(initSize);
    SocketMember member=null;
    for(int i=0;i<initSize;i++){
        Socket socket=null;
        try {
            socket = new Socket(host, port);       
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        member=new SocketMember();
        member.setSocket(socket);
        poolPart.add(member);
    }
    if(poolPart.size()>0){
    socketContainer.addAll(poolPart);
    }else{
        try {
            throw new Exception("扩大池容量失败");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    return poolPart;
}
//获取池中空闲的socket 已做线程安全处理
public SocketMember getMemberFromPool(){
    SocketMember member=null;
    //同步块获取对象
    synchronized (this) {
         for(int i=0;i<socketContainer.size();i++){
             SocketMember memberInPool=socketContainer.get(i);
             boolean inUse=memberInPool.isInUse();
             if(inUse==false){
                 memberInPool.setInUse(true);
                 member=memberInPool;
                 System.out.println("成功获取对象,在池中的位置为："+i);
                 break;
             }
         }
         //pool中没有空余
         if(member==null){
             if(socketContainer.size()<maxSize){    
                 //扩大池容量
                 List<SocketMember> newPoolPart=buildPoolPart();
                 //从新扩大的部分拿出一个来用
                 member=newPoolPart.get(0);
                 member.setInUse(true);
                 System.out.println("成功扩大池容量,当前size为:"+socketContainer.size());
             }
         }
    }
     //如果超过最大容量 等待 递归
     if(member==null){
         try {
             Thread.sleep(1000);
         }
         catch (InterruptedException e) {
             e.printStackTrace();
         }
         member=getMemberFromPool();
     }
     return member;
     
 }

public int getInitSize() {
    return initSize;
}

public void setInitSize(int initSize) {
    this.initSize = initSize;
}

public int getMaxSize() {
    return maxSize;
}

public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
}
public List<SocketMember> getSocketContainer() {
    return socketContainer;
}
public String getHost() {
    return host;
}
public int getPort() {
    return port;
}



}

