package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TcpMultiChatClient().clientStart();
	}
	public void clientStart() {
		Socket socket = null;
		try {
			socket = new Socket("192.168.36.73", 7777);
			System.out.println("서버에 연결되었습니다...");
			System.out.println();
			//---------------------------------------------
			// 쓰레드를 실행한다.
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}// clientStart()메서드 끝
	
	//-------------------------------------------
	
	//메시지 전송용 쓰레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataOutputStream dout;
		private DataInputStream din;
		
		private String name;
		private Scanner sc;
		
		//생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			sc = new Scanner(System.in);
			try {
				dout = new DataOutputStream(this.socket.getOutputStream());
				din = new DataInputStream(this.socket.getInputStream());
				
				if(din !=null) {
					// 클라이언트가 연결이 완료되면 첫번째로 '대화명'을 입력 받아서 보낸다.
					// 서버에서는 이 '대화명'이 중복되는지 여부를 검사해서 그 결과를 응답으로 클라이언트에게 보낸준다.
					while(true) {
						System.out.print("대화명 입력 : ");
						String name = sc.nextLine();
						dout.writeUTF(name); 		// 대화명 전송
						
						// 대화명의 중복여부를 응답으로 받는다.
						String resp = din.readUTF();
						
						if("대화명중복".equals(resp)) { // 대화명 중복...
							System.out.println(name + "은 중복되는 대화명입니다...");
							System.out.println("다른 대화명을 입력하세요...");
						}else { 	// 대화명이 중복되지 않을때...
							this.name = name;
							System.out.println(name + " 대화명으로 대화방에 입장했습니다.");
							break;	// 반복문 탈출...
						}
						
					}// while문 끝...
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // 생성자 끝...
		
		@Override
		public void run() {
			try {
				while(dout != null) {
				// 키보드로 입력한 내용을 서버로 전송한다.
				dout.writeUTF("[" + name + "]" + sc.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	}
	//-------------------------------------------
	
	// 메시지 수신용 쓰레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream din;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}// 생성자 끝...
		
		@Override
		public void run() {
			try {
				while(din != null) {
					// 서버가 보내온 메시지를 받아서 화면에 출력한다.
					System.out.println(din.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
}






