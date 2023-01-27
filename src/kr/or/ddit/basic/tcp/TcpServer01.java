package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {
	
	public static void main(String[] args) throws IOException {
		//TCP소켓 통신을 하기 위해 ServerSocket객체를 생성한다.(사용할 포트번호를 지정해서 생성한다.)
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버가 접속을 기다립니다...");
		
		// accept()메서드 ==> 클라이언트의 연결 요청이 올때까지 계속 기다린다.
		//				  ==> 연결 요청이 오면 새로운 Socket객체를 생성해서 클라이언트의 Socket과 연결한다.
		 Socket socket = server.accept();
		 
		 // accept()메서드 이후의 코드는 클라이언트와 연결되여야만 실행되는 코드들이다...
		 System.out.println("클라이언트와 연결되었습니다...");
		 System.out.println();
		 
		 System.out.println("접속한 상대방의 정보 확인...");
		 System.out.println("IP주소: " + socket.getInetAddress().getHostAddress());
		 System.out.println("Port번호: " + socket.getPort());
		 System.out.println();
		 
		 System.out.println("상대방과 연결된 내 컴의 정보 확인...");
		 System.out.println("IP주소: " + socket.getLocalAddress());
		 System.out.println("Port번호: " + socket.getLocalPort());
		 
		 // 클라이언트에게 메시지 보내기
		 // ==> Socket객체의 getOutputStream()메서드를 이용해서 OutputStream객체를 구해서 전송한다.
		 OutputStream out = socket.getOutputStream();
		 DataOutputStream dout = new DataOutputStream(out);
		 
		 dout.writeUTF("환영합니다...어서오세요...");
		 System.out.println("메세지를 보냈습니다...");
		 
		 //사용했던 소켓과 스트림닫기
		 dout.close();
		 socket.close();
		 server.close();
		 
	}
}






