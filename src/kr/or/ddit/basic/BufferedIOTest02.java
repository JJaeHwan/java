package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {

	public static void main(String[] args) {
		// 문자 기반의 Buffered스트림 사용하기
		try {
			// 이클립스에서 자바 프로그램이 실행되는 현재 위치는 해당 프로그램이 속한 프로젝트 폴더가
			// 현재위치가 된다.
			//.\src\kr\or\ddit\basic\FileTest01.java
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);
			
			// 문자 기반의 입력용 버퍼 스트림에는 데이터 줄 단위로 읽어올 수 있는 메서드를 제공한다.
			//		==> readLine()메서드
			
			String temp = ""; 	// 읽어온 한 줄의 데이터가 저장될 변수
			
			for(int i = 1; (temp=br.readLine())!=null; i++) {
				System.out.printf("%4d : %s\n", i, temp);
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();;
		}
		
	}

}
