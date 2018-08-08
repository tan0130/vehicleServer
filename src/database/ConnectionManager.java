package database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 利用单例模式创建数据库连接池
 * 用于连接国标测试数据库
 * @author zhao.zhihua
 *
 */
public final class ConnectionManager {

	private static ConnectionManager instance;
	private static ComboPooledDataSource dataSource;
	
	private ConnectionManager() throws PropertyVetoException {
		dataSource = new ComboPooledDataSource();
		
		dataSource.setUser("root");
		dataSource.setPassword("zhaozhihua");
//	    dataSource.setPassword("liu.yuan10");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/national_standard_test?useSSL=false");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setInitialPoolSize(5);//初始化连接数
		dataSource.setMinPoolSize(1);//最小连接数
		dataSource.setMaxPoolSize(20);//最大连接数
		dataSource.setMaxStatements(50);//最大等待时间
		dataSource.setMaxIdleTime(60);//最大空闲时间，单位毫秒
	}
	
	//获取连接管理池实例，只有一个
	public static final ConnectionManager getInstance() {
		
			try {
				if(instance == null) {
				instance = new ConnectionManager();
				}
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return instance;
	}
	
	/**
	 * final修饰方法表示不可重写，但可以被子类继承
	 * @return
	 */
	public synchronized final Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
		return conn;
	}
	
	//释放资源  
    public  void realeaseResource(ResultSet rs,Statement ps,Connection conn){  
        if(null != rs){  
            try {  
                rs.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
          
        if(null != ps){  
            try {  
                ps.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
  
        try {  
            conn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
	
	
}
