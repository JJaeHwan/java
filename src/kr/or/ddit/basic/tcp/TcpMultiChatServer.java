package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// 접속한 클라이언트 정보가 저장될 Map객체 선언
	// ==> key값 : 접속한 사람의 이름, value ==> 접속한 Socket객체
	private Map<String, Socket> clientMap;
	
	//생성자
	public TcpMultiChatServer() {
		// clientMap을 동기화 처리가 되도록 처리한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	
	public static void main(String[] args) {
		new TcpMultiChatServer().ServerStart();
		
	}
	public void ServerStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다...");
			
			while(true) {
				socket = server.accept();  // 클라이언트의 접속을 기다린다.
				System.out.println("[" + socket.getInetAddress().getHostAddress() 
						               + " : " + socket.getPort() + "]에서 접속했습니다.");
				System.out.println();
				//----------------------------------
				// 쓰레드를 작동시킨다...
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(server != null)try {server.close();} catch(IOException e) {}
		}
	} // serverStart()메서드 끝...
	
	
	// clientMap에 저장된 전체 사용자에게 메시지를 전송하는 메서드
	private void sendToAll(String msg) {
		// Map에 저장된 데이터 개수만큼 반복
		for(String name : clientMap.keySet()) {
			try {
				// key값과 같이 저장된 Socket객체를 구해서 이 Socket의 출력용 스트림객체를 구한다.
				DataOutputStream dout = new DataOutputStream(
						clientMap.get(name).getOutputStream());
				dout.writeUTF(msg);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}// sendToAll()메서드 끝...
	
	//---------------------------------------------------
	// Inner Class로 서버에서 하나의 클라이언트가 보낸 메시지를 전체의 클라이언트에게 전송하는 Thread를 작성한다.
	// ==> Inner Class로 만드는 이유 : Outer Class의 멤버를 자유롭게 사용하기 위해서... 
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
		
		//생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 수신용
				din = new DataInputStream(this.socket.getInputStream());
				
				// 통신용
				dout = new DataOutputStream(this.socket.getOutputStream());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} // 생성자
		
		
		@Override
		public void run() {
			String name = "";
			try {
				// 클라이언트가 연결이 완료되면 첫번째로 '대화명'을 입력 받아서 보낸다.
				// 서버에서는 이 '대화명'이 중복되는지 여부를 검사해서 그 결과를 응답으로 클라이언트에게 보낸준다.
				
				//클라이언트가 보내온 '대화명'이 중복되지 않을 때까지 반복한다.
				while(true) {
					name = din.readUTF();	// 클라이언트가 보낸 '대화명' 받기
					
					if(clientMap.containsKey(name)) {	//'대화명'이 중복될 경우...
						dout.writeUTF("대화명중복"); 	// '대화명중복' 메시지를 응답으로 보낸다.
						
					}else {		// 중복되지 않을 때...
						dout.writeUTF("OK");	//'OK' 메세지를 응답으로 보낸다.
						break;	// 반복문 탈출
					}
					
				}// while문의 끝...
				
				//접속한 사람의 대화명을 이용하여 다른 전체 클라이언트에게 대화방 참여 메시지를 전송한다.
				sendToAll("[" + name + "] 님이 대화방에 입장했습니다..."  );
				
				// '대화명'과 접속한 클라이언트의 'Socket객체'를 Map에 추가한다.
				clientMap.put(name, socket);
				
				System.out.println("현재 접속자 수: " + clientMap.size() + "명");
				
				// 한 클라이언트가 보낸 메시지를 받아서 전체 클라이언트에게 보낸다.
				while(din != null) {
					sendToAll(din.readUTF());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				// 이 finally영역이 실행된다는 것은 현재 클라이언트가 접속을 종료했다는 의미가 된다.
				sendToAll("[" + name + "] 님이 접속을 종료했습니다...");
				
				// 사용자 목록에서 해당 대화명을 삭제한다.
				clientMap.remove(name);
				
				System.out.println("[" + socket.getInetAddress().getHostAddress() 
			               + " : " + socket.getPort() + "]에서 접속을 종료했습니다.");
				
				System.out.println("현재 접속자 수: " + clientMap.size() + "명");
				System.out.println();
			}
			
		}
	}
	
	
	
}



















