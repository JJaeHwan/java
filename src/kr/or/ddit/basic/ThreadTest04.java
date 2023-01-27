package kr.or.ddit.basic;
/*
 	1~20억까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 때와
 	여러개의 쓰레드가 협력해서 처리할 때의 경과시간을 비교해 보자.
 */
public class ThreadTest04 {

	public static void main(String[] args) {
		//단독으로 처리하는 쓰레드
		sumThread sm = new sumThread(1L, 2_000_000_000L);
		
		//여러개의 쓰레드가 협력해서 처리하는 쓰레드
		sumThread[] smArr = new sumThread[] {
				new sumThread(		   	  1L,   500_000_000L),
				new sumThread(  500_000_001L, 1_000_000_000L),
				new sumThread(1_000_000_001L, 1_500_000_000L),
				new sumThread(1_500_000_001L, 2_000_000_000L)
		};
		
		//단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 때 경과 시간 : " + (endTime - startTime));
		System.out.println();
		
		//여러 쓰레드가 협력해서 처리하기
		startTime = System.currentTimeMillis();
		
		for(sumThread smt : smArr) {
			smt.start();
		}
		
		for(sumThread smt : smArr) {
			try {
				smt.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리할 때의 경과시간: " + (endTime - startTime));
		
		
	}

}

class sumThread extends Thread{
	private long start, end;
	
	//생성자 
	public sumThread(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = start; i <= end; i++) {
			sum += i;
		}
		System.out.println(start + "부터" + end + "까지의 합계:"+ sum);
	}
}















