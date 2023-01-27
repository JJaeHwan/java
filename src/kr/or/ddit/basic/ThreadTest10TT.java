package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Random;

public class ThreadTest10TT {

	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
			new Horse("01번말"),	
			new Horse("02번말"),	
			new Horse("03번말"),	
			new Horse("04번말"),	
			new Horse("05번말"),	
			new Horse("06번말"),	
			new Horse("07번말"),	
			new Horse("08번말"),	
			new Horse("09번말"),	
			new Horse("10번말")	
		};
		
		GameState gs = new GameState(horses);
		
		for(Horse h : horses) {
			h.start(); // 경기 시작
		}
		gs.start(); //경기상태
		
		for(Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println();
		System.out.println("경기 끝... 이랴이랴...");
		System.out.println();
		
		// 등수의 오름차순 정렬하기
		Arrays.sort(horses);  // 배열을 이용한 정렬처리
		
		// 결과 출력
		for(Horse h : horses) {
			System.out.println(h);
		}
		
		
	}

}

class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank;
	
	private String horseName;  // 말이름
	private int rank; 			// 등수
	private int location;		// 현재위치
	
	public Horse(String horseName) {
		this.horseName = horseName;
		
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return horseName + "은(는)" + rank + "등 입니다...";
	}

	@Override
	public int compareTo(Horse horse) {
		
		return Integer.compare(this.rank, horse.getRank());
	}
	
	public void run() {
		Random rnd = new Random();
		for(int i = 1; i<=50; i++ ) {
			this.location = i;
			try {
				Thread.sleep(rnd.nextInt(600));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		// 한 마리의 말의 경기가 끝나면 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;
		
	}
	
	
	
}

//경기 중 중간 중간에 각 말들의 위치를 다음과 같이 나타내시오.
// 예)
//  01번말 : --->-----------------------------------------
//  02번말 : -------->------------------------------------
// ...
//  10번말 : ----->---------------------------------------
// 경기 중에 말의 현재 위치를 나타내는 쓰레드
class GameState extends Thread{
	private Horse[] horses;		// 경주에 참가하는 말들이 저장될 배열
	
	//생성자
	public GameState(Horse[] horses) {
		this.horses = horses;
	}
	
	@Override
	public void run() {
		while(true) {
			if(Horse.currentRank == horses.length) {
				break;
			}
			for(int i =1; i <=10; i++) {
				System.out.println();
			}
			
			for(int i = 0; i < horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				
				for(int j = 1; j <= 50; j++) { // j가 구간이라고 생각
					if(horses[i].getLocation() == j) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println(); 
			}
			
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}
}





















