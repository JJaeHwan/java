package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		// SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();
		
		// 선택할 파일의 확장자 설정하기
		FileNameExtensionFilter doc = new FileNameExtensionFilter("Ms Word 파일", "docx","doc");
		FileNameExtensionFilter img = new FileNameExtensionFilter("이미지 파일", new String[] { "png","jpg","gif"});
		FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF 파일", "pdf");
		FileNameExtensionFilter txt = new FileNameExtensionFilter("TEXT 파일", "txt");
		
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(pdf);
		chooser.addChoosableFileFilter(txt);
		
		chooser.setFileFilter(txt); // 파일 목록 중 처음에 기본적으로 선택할 확장자 지정
		
		chooser.setAcceptAllFileFilterUsed(true); // 모든 파일 목록 표시 여부 설정(true: 설정, false: 해제)
		
//		chooser.setCurrentDirectory(new File("D:\\highjava\\D_other")); // 처음에 나타날 폴더 설정
		
		int result = chooser.showOpenDialog(new Panel());	// 열기용창
//		int result = chooser.showSaveDialog(new Panel());	// 저장용창
		
		// 창에서 선택한 파일 정보 확인하기
		if(result == JFileChooser.APPROVE_OPTION) {	// 창에서 '열기' 또는 '저장'버튼을 눌렀을 경우...
			File selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
			
		}
		
	}

}
