package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 	문제) 'd:/d_other' 폴더에 있는 '펭귄.jpg' 파일을
 	'd:/d_other/연습용 폴더에 '펭귄_복사본.jpg'로 복사하는 프로그램을 작성하시오.
 */
public class FileCopyTest {

	public static void main(String[] args) {
		
		// 원본 파일(읽어올 파일) 정보를 갖는 File객체 생성
		File sourceFile = new File("D:\\highjava\\D_other\\미쳤다고윤정.png");
		
		if(! sourceFile.exists()) {  //원본파일이 존재하지 않으면
			System.out.println(sourceFile.getPath() + "파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
		}
		
		
		try {
			// 원본 파일을 읽어올 스트림 객체 생성
			FileInputStream fin = new FileInputStream(sourceFile);
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			// 대상 파일(저장될 파일)에 저장할 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("D:\\highjava\\D_other\\연습용\\미쳤다고윤정_복사본.png");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			System.out.println("복사 시작...");
			
			int c; // 읽어온 데이터를 저장할 변수
			
//			while( (c = fin.read()) != -1 ) { // 원본파일을 처음부터 끝까지 읽는다
//				fout.write(c);
//			}
			while( (c = bin.read()) != -1 ) { // 원본파일을 처음부터 끝까지 읽는다
				bout.write(c);
			}
			bout.flush();
			
//			fin.close();
//			fout.close();
			bin.close();
			bout.close();
			System.out.println("복사 완료...");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
