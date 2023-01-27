package kr.or.ddit.basic;

import org.apache.log4j.Logger;

public class Log4jTest {
	// Logger 클래스의 인스턴스 구하기
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	
	public static void main(String[] args) {
		// 로그 기록 남기기
		// 형식) Logger객체변수.로그레벨명("출력할 메시지");
		logger.trace("이것은 Log4j의 TRACE레벨의 로그 메시지 입니다.");
		logger.debug("이것은 Log4j의 DEBUG레벨의 로그 메시지 입니다.");
		logger.info("이것은 Log4j의 INFO레벨의 로그 메시지 입니다.");
		logger.warn("이것은 Log4j의 WARN레벨의 로그 메시지 입니다.");
		logger.error("이것은 Log4j의 ERROR레벨의 로그 메시지 입니다.");
		logger.fatal("이것은 Log4j의 FATAL레벨의 로그 메시지 입니다.");
		System.out.println("test");
		
		
	}

}
