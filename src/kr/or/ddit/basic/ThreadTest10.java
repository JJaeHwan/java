//package kr.or.ddit.basic;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
///*
// 	 10마리의 말들이 경주하는 경마 프로그램을 작성하시오.
// 	 
// 	 말은 Horse라는 이름의 쓰레드 클래스로 작성하는데,
// 	 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
// 	 그리고, 이 클래스는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.(Comparable 인터페이스 구현)
// 	 
// 	 경주 구간은 1 ~ 50 구간으로 되어 있다. 
// 	 
// 	 경기 중 중간 중간에 각 말들의 위치를 다음과 같이 나타내시오.
// 	 예)
// 	  01번말 : --->-----------------------------------------
// 	  02번말 : -------->------------------------------------
// 	 ...
// 	  10번말 : ----->---------------------------------------
// 	  
// 	  경기가 끝나면 등수 순으로 정렬해서 출력한다.
// */
//public class ThreadTest10 {
//	static int strRank=1;
//	public static void main(String[] args) {
//		List<Horse> list = new ArrayList<>();
//		list.add(new Horse("0번마"));
//		list.add(new Horse("1번마"));
//		list.add(new Horse("2번마"));
//		list.add(new Horse("3번마"));
//		list.add(new Horse("4번마"));
//		list.add(new Horse("5번마"));
//		list.add(new Horse("6번마"));
//		list.add(new Horse("7번마"));
//		list.add(new Horse("8번마"));
//		list.add(new Horse("9번마"));
//		
//		
//		for(Horse hor : list) {
//			hor.start();
//		}
//		
//		for(Horse hs : list) {
//			try {
//				hs.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		Collections.sort(list);
//		System.out.println("===================================");
//		System.out.println();
//		System.out.println("경기 결과");
//		
//		for(Horse horse : list) {
//			System.out.println(horse.getName1() + " " + horse.getRank()+ "등");
//		}
//	}
//}
//
//
//class Horse extends Thread implements Comparable<Horse>{
//	private String name1;
//	private static int rank;
//	private int location;
//	
//	public Horse(String name) {
//		this.name1 = name;
//	}
//	
//	public String getName1() {
//		return name1;
//	}
//
//	public void setName1(String name) {
//		this.name1 = name;
//	}
//
//	public static int getRank() {
//		return rank;
//	}
//
//	public static void setRank(int rank) {
//		Horse.rank = rank;
//	}
//
//	
//
//	
//	@Override
//	public void run() {
//		for (int i = 0; i < 50; i++) {
//			System.out.println("\n" + name1 + " : ");
//			for (int j = 0; j < i; j++) {
//				System.out.print("-");
//			}
//			System.out.print(">");
//
//			for (int j = 49; j > i; j--) {
//				System.out.print("-");
//			}
//
//			System.out.println();
//
//			try {
//				Thread.sleep((int) (Math.random() * 500));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//		}
//		System.out.println(name1 + "  이랴 이랴 ~ 달리기 끄읕~");
//		
//		setRank(ThreadTest10.strRank);
//		ThreadTest10.strRank++;
//	}
//
//	@Override
//	public int compareTo(Horse hor) {
//		if (rank > hor.getRank()) {
//			return 1;
//		} else {
//			return -1;
//		}
//	}
//	
//	
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
