package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FIleIOTest05 {
	// 한글이 저장된 파일 읽어오기 ( 한글의 인코딩을 지정해서 읽어온다.)
	
	//자바는 자바 프로그램 소스 파일의 인코딩을 따라간다.
	public static void main(String[] args) {
		
		try {
//			FileReader fr = new FileReader("D:\\highjava\\D_other\\test_utf-8.txt");
//			FileReader fr = new FileReader("D:\\highjava\\D_other\\test_ansi.txt");
			
			FileInputStream fin = 
								  new FileInputStream("D:\\highjava\\D_other\\test_utf-8.txt");
//								  new FileInputStream("D:\\highjava\\D_other\\test_ansi.txt");
			
			// 기본 인코딩방식으로 읽어온다.
//			InputStreamReader isr = new InputStreamReader(fin);
			
			// 인코딩 방식을 지정해서 읽어오기
			// 형식) new InputStreamReader(기반스트림객체, 인코딩방식);
			// 인코딩방식
			// - MS949    ==> 윈도우의 기본 한글 인코딩 방식(ANSI방식과 같다.)
			// - UTF-8    ==> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식 
			
//			InputStreamReader isr = new InputStreamReader(fin, "ms949");
			InputStreamReader isr = new InputStreamReader(fin, "utf-8");
			
			int c;
			while( (c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			isr.close();	//보조 스트림을 닫으면 기반이 되는 스트림도 같이 닫힌다.
			
			
//			int c;
//			while( (c=fr.read()) != -1) {
//				System.out.print((char)c);
//			}
//			fr.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
