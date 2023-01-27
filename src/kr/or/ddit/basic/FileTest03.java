package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

// 특정 디렉토리 안의 파일 목록을 출력하는 예제
public class FileTest03 {

	public static void main(String[] args) {
		FileTest03 test = new FileTest03();
		
		File file = new File("D:\\highjava\\D_other");
//		File file = new File("c:/");
		test.displayFileList(file);
		
	}
// 디렉토리 정보를 인수값으로 받아서 해당 디렉토리에 있는 모든 파일과 디렉토리 목록을 출력하는 메서드
	public void displayFileList(File dir) {
		if(!dir.isDirectory()) {
			System.out.println("디렉토리(폴더)만 가능합니다.");
			return;
		}
		
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		
		// 해당 디렉토리 안에 있는 모든 파일과 디렉토리 목록을 구한다.
		File[] files = dir.listFiles();  // 파일 객체로 가지고 오는 것
//		String[] files = dir.list();  //스트링 배열로 가지고 오는 것
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		
		// 가져온 파일과 디렉토리 목록 개수만큼 반복
		for(int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String attr = "";		// 파일의 속성을 나타낼 변수(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = "";		// 파일의 크기가 저장될 변수
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
			}else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
				
			}
			
			System.out.printf("%s %5s %12s %s \n", df.format(new Date(files[i].lastModified())), attr, size, fileName );
			
		}
		
	}
}


















