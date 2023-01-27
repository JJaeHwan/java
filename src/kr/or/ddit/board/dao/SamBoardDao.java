package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.SamBoardVo;
import kr.or.ddit.util.DBUtil3;

public class SamBoardDao implements SamIBoardDao{
	private static SamBoardDao dao;
	
	private SamBoardDao() { }
	
	public static SamBoardDao getInstance() {
		if(dao == null) dao = new SamBoardDao();
		return dao;
	}

	@Override
	public int insertBoard(SamBoardVo boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board"
					+ " (board_no, board_title, board_writer, board_date, board_cnt, board_content) "
					+ " values (board_seq.nextval, ?, ?, sysdate, 0, ?) ";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ? ";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(SamBoardVo boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
					+ " board_title = ? , "
					+ " board_date = sysdate , "
					+ " board_content = ? "
					+ " where board_no = ? ";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<SamBoardVo> getAllBoardList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SamBoardVo> boardList = null; // 반환값
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_writer, to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ " board_cnt, board_content "
					+ " from jdbc_board order by board_no desc ";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<SamBoardVo>();
			while(rs.next()) {
				SamBoardVo boardVo = new SamBoardVo();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_content(rs.getString("board_content"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public SamBoardVo getBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SamBoardVo boardVo = null; // 반환값
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_writer, to_char(board_date, 'YYYY-MM-DD') board_date, "  
										+ " board_cnt, board_content  from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardVo = new SamBoardVo();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_content(rs.getString("board_content"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return boardVo;
	}

	@Override
	public List<SamBoardVo> getSearchBoardList(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SamBoardVo> boardList = null; // 반환값
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board "
					+ " where board_title like '%' || ? || '%' ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<SamBoardVo>();
			while(rs.next()) {
				SamBoardVo boardVo = new SamBoardVo();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_content(rs.getString("board_content"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
					+ " board_cnt = board_cnt + 1 "
					+ " where board_no = ? ";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}
	

}
