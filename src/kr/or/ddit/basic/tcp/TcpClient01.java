package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 현재 자신의 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소 : 192.168.36.77
		// 2) 지정된 IP주소 : 127.0.0.1
		// 3) 원래의 컴퓨터 이름 : 
		// 4) 지정된 컴퓨터 이름: localhost
		
		
		System.out.println("서버에 연결 중입니다...");
		
		// 연결할 서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
		// Socket객체는 생성이 완료되면 서버로 연결 요청을 보낸다.
		Socket socket = new Socket("192.168.36.77", 7777); 
		
		// 이 부분은 서버와 연결이 완료되어야만 실행되는 부분입니다...
		System.out.println("서버와 연결이 완료되었습니다...");
		System.out.println();
		
		// 서버가 보낸 메세지를 받아서 화면에 출력하기
		
		//Socket객체의 getInputStream()메서드를 이용해서 InputStream객체를 구한다.
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		// 서버가 보낸 메시지를 받아서 출력하기
		System.out.println("서버가 보낸 메시지 : " + din.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다...");
		
		// Socket과 스트림 닫기
		din.close();
		socket.close();
		
		
		
	}
}














