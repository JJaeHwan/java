package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import kr.or.ddit.util.SelectFileUtil;

// - 클라이언트는 서버와 연결되면 "D:\\highjava\\D_other" 폴더에 있는 파일을 서버로 전송하는 프로그램 작성
public class TcpFileClientDialog {

	public static void main(String[] args) {
		new TcpFileClientDialog().clientStart();
		
	}
	
	public void clientStart() {
		// 전송할 파일 지정하기
		
		// 전송할 파일 정보를 갖는 File객체 생성
//		File file = new File("D:\\highjava\\D_other\\미쳤다고윤정.png"); 
		
		File file = SelectFileUtil.showDialog("OPEN");
		
		if(file == null) {
			System.out.println("전송할 파일을 선택하지 않았습니다. 작업을 중단합니다...");
			return;
		}
		
		if(!file.exists()) {
			System.out.println(file.getPath() + " 파일이 존재하지 않습니다.");
			System.out.println("파일 전송 작업을 중단합니다...");
			return;
		}
		
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작...");
			
			// 파일명을 전송한다.
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(file.getName()); // 파일명 전송
			
			// 파일내용을 읽어올 스트림 객체 생성
			BufferedInputStream bin = new BufferedInputStream(
					new FileInputStream(file));
			
			// 서버로 전송할 스트림 객체 생성
			BufferedOutputStream bout = new BufferedOutputStream(dout);
			
			byte[] temp = new byte[1024];
			int length = 0;
			
			// 파일의 내용을 읽어서 소켓으로 출력한다.
			while((length = bin.read(temp)) > 0 ) {
				bout.write(temp, 0, length);
			}
			
			bout.flush();
			
			System.out.println("파일 전송 완료...");
			bin.close();
			bout.close();
			socket.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
