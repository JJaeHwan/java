package kr.or.ddit.util;
/*
 	JDBC 드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class 만들기
 	(dbinfo.properties파일의 내용으로 설정하
 	// 방법2) ResourceBundle객체 이용하기
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil3 {
	private static ResourceBundle bundle;	// ResourceBundle객체 변수 선언
	
	static {
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		try {
			// ResourceBundle객체 생성
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JJH96", "java");
			return DriverManager.getConnection(bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 ~~~");
			return null;
		}
	}
	
}
