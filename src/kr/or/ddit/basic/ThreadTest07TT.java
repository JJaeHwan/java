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
public class ThreadTest07TT {
	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		Random rnd = new Random();
		// 난수를 이용해서 컴퓨터의 가위 바위 보 정하기
		String[] data = {"가위", "바위", "보"};
		int index = rnd.nextInt(data.length); // 0 ~ 2 사이의 난수 만들기
		String com = data[index]; // 배열에서 난수번째 데이터를 컴퓨터의 가위 바위 보로 정한다.
		
		// 사용자의 가위 바위 보 입력 받기
		gt.start();  // 카운트 다운 시작...
		String user = null;
		do {
			user = JOptionPane.showInputDialog("(가위, 바위, 보)를 입력하세요...");
//		}while( ! ("가위".equals(user) || "바위".equals(user) || "보".equals(user)) );
		}while( ! "가위".equals(user) && ! "바위".equals(user) && ! "보".equals(user)); //각각 !을 하면 and는 or로 or는 and로 바꾼다.
		
		inputCheck = true;
		
		// 승패 판정하기
		
		String result = ""; // 승패가 저장될 변수
		if(com.equals(user)) {
			result = "비겼습니다...";
		}else if(user.equals("가위") && com.equals("보") || 
				 user.equals("바위") && com.equals("가위")||
				 user.equals("보") && com.equals("바위")) {
			result = "사용자가 이겼습니다...";
		}else {
			result = "사용자가 졌습니다...";
		}
		
		// 결과 출력하기
		System.out.println("   - 결  과 -");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + user);
		System.out.println("결  과 : " + result);
		
	}

}

class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for(int i = 15; i >=1; i--) {
			if(ThreadTest07TT.inputCheck ==true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println();
		System.out.println("-- 결과 --");
		System.out.println("시간 초과로 당신이 졌습니다...");
		System.exit(0);
		
	}
}











