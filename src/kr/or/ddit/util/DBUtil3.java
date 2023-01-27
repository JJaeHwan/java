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

import org.apache.log4j.Logger;

public class DBUtil3 {
	static Logger logger = Logger.getLogger(DBUtil3.class);
	private static ResourceBundle bundle;	// ResourceBundle객체 변수 선언
	
	static {
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		logger.info("ResourceBundle객체 생성 - dbinfo.properties파일 읽기");
		try {
			// ResourceBundle객체 생성
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			logger.info("DB 드라이버 로딩 성공");
			
		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
			logger.error("DB 드라이버 로딩 실패 ~ ~ ~ ", e);
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JJH96", "java");
			Connection conn =  DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			logger.info("DB 연결 성공 ! ! ! ");
			return conn;
			
		} catch (SQLException e) {
			logger.error("DB 연결 실패 ~ ~ ~ ");
			return null;
		}
	}
	
}
