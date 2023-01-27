package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 수신용, 송신용 패킷 객체 변수 선언
		DatagramPacket inpacket, outpacket;
		
		byte[] bMsg = new byte[512];
		
		try {
			DatagramSocket socket = new DatagramSocket();
			
			// 접속할 곳의 주소를 이용한 InetAddress 객체 생성
			InetAddress address = InetAddress.getByName("localhost"); // 내 컴퓨터 = 127.0.0.1
			
			while(true) {
				// 전송할 메시지 입력
				System.out.println("전송할 메시지 입력: ");
				String msg = sc.nextLine();
				
				// 전송용 패킷 객체 생성
				outpacket = new DatagramPacket(msg.getBytes("utf-8"), msg.getBytes("utf-8").length, address, 8888);

				// 데이터 전송...
				socket.send(outpacket);
				
				if("exit".equals(msg)) { //반복문을 끝내기 위해
					break;
				}
				
				
				//---------------------------------------------------
				// 서버에서 온 데이터를 받아서 출력하기
				
				// 수신용 패킷 객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터 수신
				socket.receive(inpacket);
				
				String receiveMsg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8");
				System.out.println("서버의 응답 메시지: " + receiveMsg);
				System.out.println();
				System.out.println();
				
			}
			
			System.out.println("클라이언트 쪽 통신 끝...");
			socket.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}











