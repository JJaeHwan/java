package kr.or.ddit.basic;

import java.util.Random;

/*
 	3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데
 	출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하시오.
  
 */

public class ThreadTest09 {

	public static void main(String[] args) {
		DisplayCharacter[] testArr = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("이몽룡")
		};
		
		for(DisplayCharacter dis : testArr) {
			dis.start();
		}
		for(DisplayCharacter dis : testArr) {
			try {
				dis.join();
			} catch (InterruptedException e) {
				
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순  위 : " + DisplayCharacter.setRank);
		
	}

}

// A ~ Z 까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	public static String setRank =""; // 출력이 끝난 순서대로 이름이 저장될 변수
	private String name;
	
	// 생성자
	public DisplayCharacter(String name) {
		this.name = name;
		
	}
	
	@Override
	public void run() {
		Random rnd = new Random();
		for(char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + "의 출력 문자 : " + c);
			try {
				Thread.sleep(rnd.nextInt(500));  // 0 ~ 500 사이의 난수가 들어가도록 설정한다.
			} catch (InterruptedException e) {
				
			} 
			
		}
		
		System.out.println(name + " 출력 끝...");
		
		// 출력이 끝난 순서대로 이름을 배치한다...
		setRank += name + "  ";
	}
	

	
	
}
















