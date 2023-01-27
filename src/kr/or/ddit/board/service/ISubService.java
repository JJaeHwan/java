package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.SubVo;

public interface ISubService {
	public int insertPost(SubVo subVo); //새글 작성
	
	public int deletePost(int number); //삭제
	
	public int updatePost(SubVo subVo); //수정
	
	public List<SubVo> getAllPost(); // 전체목록출력
	
	public SubVo ViewPost(int number); // 
	
	public List<SubVo> searchPost(String title); //검색
	
	public int setCountIncrement(int boardNo);

}
