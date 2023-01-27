package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		
		// Thread를 사용하는 방법
		// 방법1)
		// Thread클래스를 상속한 class를 작성하고 이 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start()메서드를 호출해서 실행한다.
		MyThread1 th1 = new MyThread1(); //이 class의 인스턴스를 생성한 후
		th1.start();
		
		// 방법 2-1
		// Runnable 인터페이스를 구현한 class를 작성하고 이 class의 인스턴스를 생성한다.
		// 그리고 Thread클래스의 인스턴스를 생성할 때 Runnable을 구현한 클래스의 인스턴스를 생성자에
		// 넣어서 생성한다. 이때 Thread클래스 인스턴스의 start()메서드를 호출해서 실행한다.
		MyRunner1 r = new MyRunner1();
		Thread th2 = new Thread(r);
		th2.start();
		
		//방법 2-2 ==> 익명 구현체(1회성)
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i <= 200; i++) {
					System.out.println("^_^");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					
					}
				}
			}
		};
		Thread th3 = new Thread(r2);
		th3.start();
		
		
		System.out.println("main메서드 끝...");
	}

}


// 방법1 ==> Thread클래스를 상속한 class를 작성
class MyThread1 extends Thread{
	@Override
	public void run() {
		// 이 run()메서드를 재정의하는데 이 메서드에는 쓰레드가 처리할 내용을 기술하면 된다.
		
		for(int i = 1; i <=200; i++) {
			System.out.println("바");
			try {
				// Thread.sleep(시간)메서드는 주어진 시간동안 작업을 잠시 멈춘다.
				//		'시간'은 밀리세컨드 단위를 사용한다.(즉, 1000은 1초를 의미한다.)
				Thread.sleep(100);
			} catch (InterruptedException e) {
			
			}
		}
		
	}
}


//방법2 ==> Runnable 인터페이스를 구현한 class를 작성
class MyRunner1 implements Runnable{

	@Override
	public void run() {
		//쓰레드가 처리할 내용을 작성한다.
		for(int i = 1; i <=200; i++) {
			System.out.print("보");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			
			}
		}
	}
	
}


