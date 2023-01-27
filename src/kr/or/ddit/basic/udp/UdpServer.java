package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 	UDP방식 : 비연결 지향, 데이터에 대한 신뢰성 없다.
 			  데이터가 순서대로 도착한다는 보장이 없다. 그렇지만 TCP방식보다 속도가 빠르다.
 			  
 	 - DatagramSocket객체와 DatagramPacket객체를 이용하여 통신한다.
 	 	 * DatagramSocket : 데이터의 송신과 수신에 관련된 작업을 수행한다. (우체부 역할)
 	 	 * DatagramPacket : 실제 주고 받는 데이터와 관련된 작업을 수행한다. (소포, 편지)
 	 	 					==> 수신용과 송신용의 DatagramPacket이 있는데 송신용과 수신용은
 	 	 						생성자를 이용해서 구분한다. (즉, 송신용 생성자와 수신용 생성자를 따로 제공한다.)
 	 	 						
 	 - TCP의 경우에는 스트림객체를 이용해서 송수신 하지만 
 	   UDP의 경우에는 데이터그램을 이용해서 송수신 한다.
 */

public class UdpServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용 패킷객체변수와 송신용 패킷 객체변수 선언
			DatagramPacket inpacket, outpacket;
			
			System.out.println("서버가 실행중...");
			
			while(true) {
				// 데이터가 저장될 byte형 배열선언
				byte[] bMsg = new byte[512];
				
				// 수신용 패킷객체 생성
				//  ==> 생성자에 제공할 인수값 : 수신한 데이터가 저장될 byte형 배열, 배열의 길이
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터 수신하기 ==> receiver() 메서드 이용 
				// 		==> 이 receive() 메서드는 데이터가 올 때까지 기다린다.
				// 		==> 데이터가 들어오면 수신된 데이터의 패킷 정보를 지정한 패킷 변수에 저장한다.
				socket.receive(inpacket);
				
				// 수신 받은 패킷에서 상대방의 주소, 포트번호 등을 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대방의 IP 정보 : " + address);
				System.out.println("상대방의 Port번호 : " + port);
				
				// 상대방이 보낸 데이터(메시지)	받기
				// inpacket.getLength() ==> 실제 읽어온 데이터의 길이를 반환한다.
				// inpacket.getData()	==> 실제 읽어온 데이터를 byte형 배열로 반환한다.
				//					(실제 읽어온 데이터는 수신용 패킷객체를 생성할 때 지정한 byte형 배열에도 저장된다.)
				
				// 수신받은 데이터가 byte형 배열이기 때문에 이 배열의 데이터를 String형으로 변환한다.
				// 형식) new String(byte형 배열, 시작위치, 길이, 인코딩방식)
//				String msg = new String(inpacket.getData(),0,inpacket.getLength(),"utf-8"); 
				String msg = new String(bMsg,0,inpacket.getLength(),"utf-8");
				
				System.out.println("상대방이 보낸 메시지 : " + msg);
				System.out.println();
				
				// 클라이언트가 보낸 메시지가 'exit'이면 통신 끝...
				if("exit".equals(msg)) {
					break;
				}
				
				//-------------------------------------------------------------
				
				// 상대방에게 메시지 보내기( 수신 받은 메세지를 그대로 송신하기)
				
				// 송신할 메시지를 byte형 배열로 변환한다.
				byte[] sendMsg = msg.getBytes("utf-8"); // getBytes가 바꿔줌
				
				// 송신용 패킷 객체 생성
				//		==> 생성자에게 제공할 인수값 : 전송할 데이터가 저장될 byte형 배열, 전송할 자료의 길이
				// 									  상대방의 주소정보(InetAddress객체값), 포트번호
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				//송신하기 ==> send()메서드 이용
				socket.send(outpacket);
				
				System.out.println("송신완료...");
				System.out.println("---------------------------------------------------");
				
				
				
				
			}
			System.out.println("서버쪽 통신 끝...");
			socket.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}















