package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//이 클래스는 소켓을 통해서 데이터(메시지)를 보내는 역할만 하는 쓰레드이다.
public class Sender extends Thread {
	private Socket socket;
	private DataOutputStream dout;
	
	private String name;
	private Scanner sc;
	
	//생성자
	public Sender(Socket socket) {
		this.socket = socket;
		sc = new Scanner(System.in);
		
		System.out.print("이름 입력 : ");
		name = sc.nextLine();
		try {
			
			dout = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(dout != null) {
			try {
				dout.writeUTF(name+ " : " + sc.nextLine()); 
			} catch (Exception e) {
				
			}
		}
	}
		
		

}
