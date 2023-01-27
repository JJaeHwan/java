package kr.or.ddit.basic;

public class ThreadTest15 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();	//  공통으로 사용할 객체 생성
		
		TestThread th1 = new TestThread("Test1", sObj);
		TestThread th2 = new TestThread("테스트2", sObj);
		
		th1.start();
		th2.start();
	}
}

class TestThread extends Thread{
	private ShareObject sObj;
	
	// 생성자
	public TestThread(String name, ShareObject sObj) {
		super(name);		// 쓰레드의 name속성 설정
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i ++) {
			sObj.add();
		}
	}
}





// 공통으로 사용할 객체
class ShareObject{
	private int sum = 0;
	
	// 동기화 처리하기
	
	// 방법1) 메서드 자체에 동기화 설정을 한다.
	
//	public synchronized void add() {
	public void add() {
		// 방법2) 동기화 블럭으로 설정한다.
		synchronized (this) { //()안에 객체
			
			int n = sum;
			
			n += 10;
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
}











