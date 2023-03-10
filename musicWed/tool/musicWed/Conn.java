package musicWed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

	static String url="jdbc:mysql://localhost:3306/music?user=root&password=2610"
			+"&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
	
	static Connection getConn(){
		try{
			return DriverManager.getConnection(url);
					
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
