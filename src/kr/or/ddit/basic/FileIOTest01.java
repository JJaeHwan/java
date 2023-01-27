package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// 바이트 기반의 스트림을 이용하여 파일 내용 읽기
		try {
			// 읽어올 파일 정보를 인수값으로 받는 스트림객체 생성
			
			// 방법1
			FileInputStream fin = new FileInputStream("D:\\highjava\\D_other\\test.txt");
			
			// 방법2
			File file = new File("D:\\highjava\\D_other\\test.txt");
			FileInputStream fin2 = new FileInputStream(file);
			
			int c;	// 읽어온 데이터가 저장될 변수
			
			while( (c = fin.read()) != -1) { // 바이트 기반이나 문자 기반이나 사용법은 같다
				
				// 읽어온 문자를 화면에 출력하기
				System.out.print((char)c);
			}
			fin.close(); 	// 스트림 닫기
			
			
		} catch (IOException e) {
			System.out.println(" 오 류 오 류 오 류 오 류 오 류 오 류 오 류 오 류 오 류 오 류 오 류");
		}

	}

}
