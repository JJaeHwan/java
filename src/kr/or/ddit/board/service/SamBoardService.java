package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.SamBoardDao;
import kr.or.ddit.board.vo.SamBoardVo;

public class SamBoardService implements SamIBoardService  {
	private SamBoardDao dao;
	
	private static SamBoardService service;
	
	private SamBoardService() {
		dao = SamBoardDao.getInstance();
	}
	
	public static SamBoardService getInsatance() {
		if(service == null) service = new SamBoardService();
		return service;
	}

	@Override
	public int insertBoard(SamBoardVo boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(SamBoardVo boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public List<SamBoardVo> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public SamBoardVo getBoard(int boardNo) {
		
		int cnt = dao.setCountIncrement(boardNo);
		SamBoardVo boardVo = null;	// 반환값이 저장될 변수
		if(cnt > 0) {	// 조회수 증가가 성공했을때
			boardVo = dao.getBoard(boardNo);
		}
		return boardVo;
	}

	@Override
	public List<SamBoardVo> getSearchBoardList(String title) {
		return dao.getSearchBoardList(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {	// 사실 이거 필요 없음 ㅎ
		return dao.setCountIncrement(boardNo);
	}


}
