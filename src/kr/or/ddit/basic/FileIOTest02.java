package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("D:\\highjava\\D_other\\fileout.txt");
			
			for(char ch = 'A'; ch <= 'Z'; ch++) {
				fout.write(ch);	// ch변수의 데이터를 파일로 출력한다.
				
			}
			System.out.println("출력완료...");
			fout.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
