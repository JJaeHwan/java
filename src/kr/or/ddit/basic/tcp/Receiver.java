package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.net.Socket;

// 이 클래스는 소켓을 통해서 데이터(메시지)를 받아서 화면에 출력하는 역할만 하는 쓰레드
public class Receiver extends Thread {
	private Socket socket;
	private DataInputStream din;
	
	public Receiver(Socket socket) {
		this.socket = socket;
	
		try {
			din = new DataInputStream(this.socket.getInputStream());
		} catch (Exception e) {
		
		}
	}
	
	@Override
	public void run() {
		while(din != null) {
			try {
				System.out.println(din.readUTF());
			} catch (Exception e) {
				
			}
		}
	}
	
}
