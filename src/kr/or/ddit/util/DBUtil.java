package kr.or.ddit.util;
/*
 	JDBC 드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class 만들기
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JJH96", "java");
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 ~~~");
			return null;
		}
	}
	
}
