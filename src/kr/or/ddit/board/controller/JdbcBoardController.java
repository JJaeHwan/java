package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.SamBoardService;
import kr.or.ddit.board.vo.SamBoardVo;

public class JdbcBoardController {
	private SamBoardService service;
	private Scanner sc;
	
	public JdbcBoardController() {
		sc = new Scanner(System.in);
		service = SamBoardService.getInsatance();
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
		
	}
	
	// 시작 메서드
	public void boardStart() {
		String searchData = null;
		while(true) {
			int choice = displayMenu(searchData);
			
			switch (choice) {
			case 1:  // 새글작성
				insertBoard(); searchData =null;
				break;
			case 2:  // 게시글보기
				viewBoard(); searchData =null;
				break;
			case 3:  // 검색
				searchData = searchBoard();
				break;
			case 0:
				System.out.println("게시판 프로그램 종료...*^^*");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요...");
			}
		}
	}
	
	// 검색 메서드
	private String searchBoard() {
		sc.nextLine();
		System.out.println();
		System.out.println("검색작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		return sc.nextLine();
		
	}

	private void viewBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >>");
		int boardNo = sc.nextInt();
		
		SamBoardVo boardVo = service.getBoard(boardNo);
		if(boardVo == null) {
			System.out.println(boardNo + "번 게시글이 존재하지 않네요 ? ^^;");
			return;
		}
		System.out.println();
		System.out.println(boardNo + "번 게시글 내용");
		System.out.println("------------------------------------------------------------");
		System.out.println("- 제  목 : " + boardVo.getBoard_title());
		System.out.println("- 작성자 : " + boardVo.getBoard_writer());
		System.out.println("- 내  용 : " + boardVo.getBoard_content());
		System.out.println("- 작성일 : " + boardVo.getBoard_date());
		System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("------------------------------------------------------------");
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print("작업선택 >> ");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1:	// 수정
			updateBoard(boardNo);
			break;
		case 2:	// 삭제
			deleteBoard(boardNo);
			break;
		case 3:
			return;
		default:
			System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요...");
		}
	}
	
	// 게시글을 삭제하는 메서드
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번 글이 삭제되었습니다.");
		}else {
			System.out.println(boardNo + "번 글 삭제 실 패  ~ ~ ~ ");
		}
		
	}

	// 게시글을 수정하는 메서드
	private void updateBoard(int boardNo) {
		sc.nextLine();
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = sc.nextLine();
		
		System.out.print("- 내  용 : ");
		String content = sc.nextLine();
		
		SamBoardVo boardVo = new SamBoardVo();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번 글이 수정되었습니다.");
		}else {
			System.out.println(boardNo + "번 글 수정 실 패  ~ ~ ~ ");
		}
		
		
		
	}

	// 새글을 작성해서 저장하는 메서드
	private void insertBoard() {
		sc.nextLine();	// 입력 버퍼 비우기
		System.out.println();
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = sc.nextLine();
		
		System.out.print("- 작성자 : ");
		String writer = sc.nextLine();
		
		System.out.print("- 내  용 : ");
		String content = sc.nextLine();
		
		// 입력 받은 정보를 VO에 담는다.
		SamBoardVo boardVo = new SamBoardVo();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println("새글이 추가되었습니다...");
		}else {
			System.out.println("새 글 추가 실패 ~~~");
		}
		
	}

	// 게시판 목록을 보여주고 메뉴를 출력하며 작업 번호를 반환하는 메서드
	private int displayMenu(String searchData) {
		List<SamBoardVo> boardList;
		if(searchData ==null) {
			// 게시판 전체 목록 가져오기
			boardList = service.getAllBoardList();
			
		}else {
			// 검색한 목록 가져오기
			boardList = service.getSearchBoardList(searchData);
		}
		
		System.out.println("------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수");
		System.out.println("------------------------------------------------------------");
		
		if(boardList == null || boardList.size() == 0) {
			System.out.println("  출력할 게시글이 하나도 없습니다...^_^");
		}else {
			for( SamBoardVo boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t"
						+ boardVo.getBoard_title() + "\t"
						+ boardVo.getBoard_writer() + "\t"
						+ boardVo.getBoard_cnt()    ); 

			}
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >> ");
		return sc.nextInt();
		
	}
	

}










