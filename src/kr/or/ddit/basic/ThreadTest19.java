package kr.or.ddit.basic;

public class ThreadTest19 {

	public static void main(String[] args) {
		DataBox box = new DataBox();
		
		ProducerThread th1 = new ProducerThread(box);
		ConsumerThread th2 = new ConsumerThread(box);
		
		th1.start();
		th2.start();
		
	}
}

// 데이터를 공통으로 사용할 클래스
class DataBox{
	private String data;
	
	// data 변수의 값이 null이면 data변수에 문자열이 채워질 때가지 기다리고 
	// data 변수에 값이 있으면 해당 문자열을 반환한다.
	// 반환 후에 data변수의 값은 null로 변경한다.
	public synchronized String getData() {
		if(data == null) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		//  data변수에 값이 있을 때...
		String temp = data;
		System.out.println("쓰레드가 읽은 데이터: " + temp );
		data = null;
		
		notify();
		
		return temp;
		
	}
	
	// data변수에 값이 있으면 data변수값이 null이 될때가지 기다린다.
	// data변수의 값이 null이 되면 새로운 값을 data변수에 저장한다.
	public synchronized void setData(String str) {
		if(data != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		 // data변수값이 null 일 때...
	      data = str;
	      System.out.println("Thread에서 새로 저장한 데이터 : " + data);
	      
	      notify();

		
	}
	
	
}
class ProducerThread extends Thread	{
	private DataBox databox;

	public ProducerThread(DataBox databox) {
		super();
		this.databox = databox;
	}
	@Override
	public void run() {
		String[] dataArr = {"홍길동", "아이유", "고윤정", "강감찬", "이몽룡"};
		for(int i = 0; i < dataArr.length; i++ ) {
			databox.setData(dataArr[i]);
		}
	
	}
}

// 데이터를 가져와서 사용하는 쓰레드
class ConsumerThread extends Thread{
	private DataBox databox;

	public ConsumerThread(DataBox databox) {
		super();
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			String str = databox.getData();
		}
	
	}
}











