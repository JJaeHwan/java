package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		
		// 입출력 성능 향상을 위해서 Buffered스트림을 사용한다. 
		try {
			FileOutputStream fout = new FileOutputStream("D:\\highjava\\D_other\\bufferTest.txt");
			
			// 버퍼의 크기가 5인 스트림 객체 생성
			// 버퍼의 크기를 정하지 않으면 기본 크기로 만들어진다.(8192byte = 8KB)
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(char i = '1'; i <= '9'; i++) {
//				fout.write(i);
				bout.write(i);
			}
			bout.flush(); //버퍼에 남아 있는 데이터를 강제로 모두 출력시킨다.
			
//			fout.close();  // 보조 스트림을 닫으면 보조스트림이 사용한 기반이 되는 스트림도 같이 닫혀서 굳이 안닫아도 된다.
			bout.close(); // close() 안에는 flush()기능이 들어있다.
//							그치만 중간에 어떤 작업이 필요할때는 close전에 flush가 필요하다
			
			System.out.println("작업 끝...");
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
