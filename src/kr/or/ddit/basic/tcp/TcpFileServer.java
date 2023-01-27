package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//서버는 클라이언트가 보내온 데이터를 받아서 "D:\\highjava\\D_other\\down" 폴더에 같은 이름으로 저장하는 프로그램을 작성하시오.
public class TcpFileServer {

	public static void main(String[] args) {
		new TcpFileServer().serverStart();
		
	}
	
	public void serverStart() {
		File file = new File("D:\\highjava\\D_other\\down");
		
		//저장할 폴더가 없으면 새로 생성한다.
		if(!file.exists()) {
			file.mkdirs();
		}
		
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다...");
			
			Socket socket = server.accept();
			
			// 클라이언트가 처음에 전송한 '파일명'을 받는다.
			DataInputStream din = new DataInputStream(socket.getInputStream());
			String fileName = din.readUTF();
			
			// 저장할 폴더와 클라이언트가 보낸 파일명을 이용한 File객체 생성
			File saveFile = new File(file, fileName);
			
			// 클라이언트가 보내온 파일 내용을 받아서 지정된 위치에 저장하기
			
			
			// 소켓으로 받은 파일 내용을 저장할 스트림 객체 생성
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			// 소켓으로 파일 내용을 받기위한 스트림 객체 생성
			BufferedInputStream bin = new BufferedInputStream(din);
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 소켓으로 데이터를 받아서 파일로 출력한다.
			while((len = bin.read(temp)) > 0) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			
			System.out.println("파일 다운로드 완료...");
			
			socket.close();
			din.close();
			bout.close();
			bin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
