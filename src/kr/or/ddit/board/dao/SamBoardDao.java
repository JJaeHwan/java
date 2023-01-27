package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.SamBoardVo;
import kr.or.ddit.util.SqlMapClientFactory;

public class SamBoardDao implements SamIBoardDao{
	private static SamBoardDao dao;
	
	private SamBoardDao() { }
	
	public static SamBoardDao getInstance() {
		if(dao == null) dao = new SamBoardDao();
		return dao;
	}

	@Override
	public int insertBoard(SamBoardVo boardVo) {
		SqlMapClient smc = null;
		smc = SqlMapClientFactory.getSqlMapClient();
		int cnt = 0;
		
		try {
			Object obj = smc.insert("board.insertBoard", boardVo);
			
			if(obj==null) {
				cnt =1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	

	@Override
	public int deleteBoard(int boardNo) {
		SqlMapClient smc = null;
		smc = SqlMapClientFactory.getSqlMapClient();
		int cnt = 0;
		
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(SamBoardVo boardVo) {
		SqlMapClient smc = null;
		smc = SqlMapClientFactory.getSqlMapClient();
		int cnt = 0;
		
		try {
			cnt = smc.update("board.updateBoard", boardVo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<SamBoardVo> getAllBoardList() {
		List<SamBoardVo> boardList = null; // 반환값
		SqlMapClient smc = null;
		smc = SqlMapClientFactory.getSqlMapClient();
		
		try {
			
			boardList = smc.queryForList("board.getAllBoardList");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public SamBoardVo getBoard(int boardNo) {
		SqlMapClient smc = null;
		smc = SqlMapClientFactory.getSqlMapClient();
		SamBoardVo boardVo = null; // 반환값
		
		try {
			boardVo = (SamBoardVo) smc.queryForObject("board.getBoard", boardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		
		return boardVo;
	}

	@Override
	public List<SamBoardVo> getSearchBoardList(String title) {
		List<SamBoardVo> boardList = null; // 반환값
		SqlMapClient smc = null;
		smc = SqlMapClientFactory.getSqlMapClient();
		
		try {
			
			boardList = smc.queryForList("board.searchBoardList", title);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}
	
	@Override
	public int setCountIncrement(int boardNo) {
		SqlMapClient smc = null;
		smc = SqlMapClientFactory.getSqlMapClient();
		int cnt = 0;
		
		try {
			cnt = smc.update("board.setCountIncrement", boardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	

}
