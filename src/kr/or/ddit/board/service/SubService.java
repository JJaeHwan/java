package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.SubDao;
import kr.or.ddit.board.vo.SubVo;

public class SubService  implements ISubService{
	private SubDao dao;
	
	private static SubService service;
	private SubService() {}
	public static SubService getInstance() {
		if(service == null) service = new SubService();
		return service;
	}
	
	
	@Override
	public int insertPost(SubVo subVo) {
		return dao.insertPost(subVo);
	}

	@Override
	public int deletePost(int number) {
		return dao.deletePost(number);
	}

	@Override
	public int updatePost(SubVo subVo) {
		return dao.updatePost(subVo);
	}

	@Override
	public List<SubVo> getAllPost() {
		return dao.getAllPost();
	}

	@Override
	public SubVo ViewPost(int number) {
		int cnt = dao.setCountIncrement(number);
		SubVo boardVo = null;
		if(cnt > 0) {
			boardVo = dao.ViewPost(number);
		}
		return boardVo;
	}

	@Override
	public List<SubVo> searchPost(String title) {
		return dao.searchPost(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
