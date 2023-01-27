package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) {
	// ServerSocket객체를 만들고 클라이언트의 접속을 기다리다
	// 클라이언트의 접속 요청이 오면 접속된 Socket객체를 구해서
	// 메세지를 받는 쓰레드와 메세지를 보내는 쓰레드객체를 생성할때
	// 이 Socket을 넣어준다.
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버가 준비 중입니다...");
			
			Socket socket = server.accept();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
