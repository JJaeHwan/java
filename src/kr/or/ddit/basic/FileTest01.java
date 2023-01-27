package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		//File객체 만들기 연습
		
		//1. new File(String 파일 또는 경로)
		//  ==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 슬레쉬('/')를 사용하거나
		//		역슬레쉬('\')를 사용할 수 있다.
//		File file1 = new File("D:/highjava/D_other/test.txt"); //구분문자는 슬레쉬('/')를 사용
		File file1 = new File("D:\\highjava\\D_other\\test.txt"); //구분문자는 역슬레쉬('\')를 사용
		
		System.out.println("파일명: " + file1.getName());
		System.out.println("파일일까? : " + file1.isFile());
		System.out.println("디렉토리일까?" + file1.isDirectory());
		System.out.println();
		
		File file2 = new File("D:/highjava/D_other");
		System.out.println("파일명: " + file2.getName());
		System.out.println("파일일까? " + file2.isFile());
		System.out.println("디렉토리일까? " + file2.isDirectory());
		System.out.println();
		
		// 2. new File(File parent, String child)
		// 		==> 'parent' 디렉토리 안의 'child'파일을 갖는다.
		File file3 = new File(file2, "test.txt");
		System.out.println("파일명: " + file3.getName());
		System.out.println("파일일까? : " + file3.isFile());
		System.out.println("디렉토리일까?" + file3.isDirectory());
		System.out.println();
		
		// 2. new File(String parent, String child)
		File file4 = new File("D:/highjava/D_other","test.txt");
		System.out.println("파일명: " + file4.getName());
		System.out.println("파일일까? : " + file4.isFile());
		System.out.println("디렉토리일까?" + file4.isDirectory());
		System.out.println("-----------------------------------------");
		
//		------------------------------------------------------------------
		
		//디렉토리(폴더) 만들기
		/*
		 	- mkdir() 	==> File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 			  	==> 반환값 : 만들기 성공(true), 만들기 실패(false)
		 			  	==> 중간 부분의 경로가 모두 만들어져 있어야 마지막 위치의 경로를 만들 수 있다.
		 			  	
		 	- mkdirs()	==> 중간 부분의 경로가 없으면 중간 부분의 경로도 같이 만들어 준다. 
		 */
		
		File file5 = new File("D:/highjava/D_other/연습용");
		
		if( !file5.exists()) {
			if( file5.mkdir() ) {
			System.out.println(file5.getName() + " 만들기 성공 !!!");
			}else {
			System.out.println(file5.getName() + " 만들기 실패 ~~~");
			}
		}else {
			System.out.println(file5.getName() + " 은 이미 있습니다.");
		}
		System.out.println();
		
		File file6 = new File("D:/highjava/D_other/test/java/src");
		if( ! file6.exists()) {
			if(file6.mkdirs()) {
				System.out.println(file6.getName() + " 만들기 성공 !!!");
			}else {
				System.out.println(file6.getName() + " 만들기 실패 ~~~");
			}
		}else {
			System.out.println(file5.getName() + " 은 이미 있습니다.");
		}
		System.out.println();
		
		
	}

}





