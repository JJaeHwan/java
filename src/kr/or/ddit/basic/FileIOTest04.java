package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		try {
//			System.in ==> 콘솔(표준 입출력 장치)의 입력 장치와 연결된 스트림 객체
			
			// 입력용 바이트 기반 스트림을 문자기반 스트림으로 변환해 준다.
			InputStreamReader isr = new InputStreamReader(System.in);
			
			// 문자기반의 출력용 스트림 객체 생성
			FileWriter fw = new FileWriter("D:\\highjava\\D_other\\testChar.txt");
			
			System.out.println("아무내용이나 입력해주세요(입력의 끝은 Ctrl + Z 입니다.)");
			
			int c;
			
			// 콘솔에서 입력할 때 입력의 끝은 'Ctrl + Z' 키를 누르면 된다.
			while( (c = isr.read()) != -1) {
				fw.write(c);  // 콘솔로 입력받은 데이터를 파일에 출력한다.
			}
			isr.close();
			fw.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
	}
}
