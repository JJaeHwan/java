package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.SamBoardVo;

public interface SamIBoardDao {
	
	
	
	public int insertBoard(SamBoardVo boardVo);
	
	/**
	 * 인수값으로 받은  게시판 번호를 이용하여 해당 게시글을 삭제하는 메서드
	 * 
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 수정할 데이터를 인수값으로 받아서 해당 게시글을 수정하는 메서드
	 * 
	 * @param boardVo 수정할 데이터가 저장될 SamBoardVo객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateBoard(SamBoardVo boardVo);
	
	/**
	 * JDBC_BOARD테이블의 전체 데이터를 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return 테이블 전체의 SamBoardVo객체가 저장된 List객체  
	 */
	public List<SamBoardVo> getAllBoardList();
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글 내용을 반환하는 메서드
	 * 
	 * @param boardNo 검색할 게시글 번호
	 * @return 검색된 게시글 정보를 저장한 SamBoardVo객체
	 */
	public SamBoardVo getBoard(int boardNo);
	
	/**
	 * 게시글의 제목을 인수값으로 받아서 게시글을 검색하는 메서드
	 * 
	 * @param title 검색할 게시글 제목
	 * @return 검색된 결과를 담은 List객체
	 */
	public List<SamBoardVo> getSearchBoardList(String title);
	
	/**
	 * 게시글 번호를 인수값으로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * 
	 * @param boardNo 조회수를 증가할 게시글의 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0 
	 */
	public int setCountIncrement(int boardNo);
}
