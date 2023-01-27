package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			//available() 메서드 ==> 읽어올 수 있는 데이터의 개수를 반환
			while(input.available() > 0) {
//				input.read(temp);
//				output.write(temp);
				
				int len = input.read(temp);	//read(byte[] 배열) ==> 실제 읽어온 byte수를 반환한다.
				
				// temp배열의 데이터 중 0번째부터 len개수만큼 출력한다.
				output.write(temp, 0, len);
				
				
				System.out.println("반복문 안에서 temp = " +Arrays.toString(temp) );
				
			}
			outSrc = output.toByteArray();
			
			System.out.println();
			System.out.println(" inSrc = " + Arrays.toString(inSrc));
			System.out.println("outSrc = " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
/*
반복문 안에서 temp = [0, 1, 2, 3]
반복문 안에서 temp = [4, 5, 6, 7]
반복문 안에서 temp = [8, 9, 6, 7]
초기화를 안하고 덮어씌운거라 마지막에 6,7이 남아있음
 */ 

