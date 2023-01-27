package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 	 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 	 
 	 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고 
 	 사용자의 가위 바위 보는 showInputDialog()메서드를 이용해서 입력 받는다.
 	 
 	 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 	 5초안에 입력이 없으면 게임에 진것으로 처리한다.
 	 
 	 5초안에 입력에 완료되면 승패를 구해서 결과를 출력한다.
 	 
 	 결과 예시)
 	 1) 5초 안에 입력을 못했을 경우 
 	 	- 결  과 - 
 	 	시간 초과로 당신이 졌습니다...
 	 	
 	 2) 5초 안에 입력이 되었을 경우
 	 	- 결  과 - 
 	 	컴퓨터 : 가위
 	 	사용자 : 바위
 	 	결  과 : 당신이 이겼습니다.
 */
public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th1 = new User();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
		
	}

}

class User extends Thread{
	public static boolean inputCheck = false;
	Random rnd = new Random();
	String com;
	@Override
	public void run() {
		
		String str = JOptionPane.showInputDialog("(가위, 바위, 보) 선택하세요");
		inputCheck = true;
		
		System.out.println("사용자 : " + str);
		
		int compu = rnd.nextInt(2);
		if(compu == 0) {
			com = "가위";
		}else if(compu == 1) {
			com = "바위";
		}else if(compu ==2) {
			com = "보";
		}
		
		System.out.println("컴퓨터 : " + com);
		System.out.println("- 결  과 -");
		
		if(str.equals(com) ) {
			System.out.println("비겼네요?");
		}else if(str.equals("가위") || com.equals("바위")) {
			System.out.println("컴퓨터 승리");
		}else if(str.equals("가위") || com.equals("보")) {
			System.out.println("너 승리");
		}else if(str.equals("보") || com.equals("가위")) {
			System.out.println("컴퓨터 승리");
		}else if(str.equals("보") || com.equals("바위")) {
			System.out.println("너 승리");
		}else if(str.equals("바위") || com.equals("보")) {
			System.out.println("컴퓨터 승리");
		}else if(str.equals("바위") || com.equals("가위")) {
			System.out.println("너승리");
		}
			
	
	}
}

class CountDown extends Thread{
	@Override
	public void run() {
		for(int i = 5; i >=1; i --) {
			if(User.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("- 결  과 -");
		System.out.println("시간 초과로 당신이 졌습니다...");
		
	
	}
	

}

class Computer extends Thread{
	
	@Override
	public void run() {
		
	
	}
		
}








