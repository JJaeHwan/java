package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import kr.or.ddit.board.vo.SamBoardVo;
import kr.or.ddit.board.vo.SubVo;
import kr.or.ddit.util.DBUtil3;

public class SubDao implements ISubDao {
	
	private static SubDao dao;
	private SubDao() {}
	public static SubDao getInstance() {
		if(dao == null) dao = new SubDao();
		return dao;
	}
	
	

	@Override
	public int insertPost(SubVo subVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "INSERT INTO JDBC_BOARD"
					+ " (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT) "
					+ " VALUES (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, 0, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subVo.getTitle());
			pstmt.setString(2, subVo.getWriter());
			pstmt.setString(3, subVo.getContent());
			
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
	public int deletePost(int number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			
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
	public int updatePost(SubVo subVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE JDBC_BOARD SET"
					+ " BOARD_TITLE = ? ,"
					+ " BOARD_DATE = SYSDATE ,"
					+ " BOARD_CONTENT = ?"
					+ " WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subVo.getTitle());
			pstmt.setString(2, subVo.getContent());
			pstmt.setInt(3, subVo.getNumber());
			
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
	public List<SubVo> getAllPost() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SubVo> boardList = null;	// 반환값 저장
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD ORDERBY BOARD_NO DESC";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<SubVo>();
			while(rs.next()) {
				SubVo boardVo = new SubVo();
				boardVo.setNumber(rs.getInt("BOARD_NO"));
				boardVo.setTitle(rs.getString("BOARD_TITLE"));
				boardVo.setWriter(rs.getString("BOARD_WRITER"));
				boardVo.setContent(rs.getString("BOARD_CONTENT"));
				boardVo.setCnt(rs.getInt("BOARD_CNT"));
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
	public SubVo ViewPost(int number) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubVo boardVo = null; // 반환값
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_writer, board_date, board_date, "  
										+ " board_cnt, board_content  from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardVo = new SubVo();
				boardVo.setNumber(rs.getInt("board_no"));
				boardVo.setTitle(rs.getString("board_title"));
				boardVo.setWriter(rs.getString("board_writer"));
				boardVo.setDate(rs.getString("board_date"));
				boardVo.setContent(rs.getString("board_content"));
				boardVo.setCnt(rs.getInt("board_cnt"));
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
	public List<SubVo> searchPost(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SubVo> boardList = null; // 반환값
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD "
					+ "WHERE BOARD_TITLE LIKE '%' || ? || '%' ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<SubVo>();
			while(rs.next()) {
				SubVo boardVo = new SubVo();
				boardVo.setNumber(rs.getInt("BOARD_NO"));
				boardVo.setTitle(rs.getString("BOARD_TITLE"));
				boardVo.setWriter(rs.getString("BOARD_WRITER"));
				boardVo.setDate(rs.getString("BOARD_DATE"));
				boardVo.setContent(rs.getString("BOARD_CONTENT"));
				boardVo.setCnt(rs.getInt("BOARD_CNT"));
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
			String sql = "UPDATE JDBC_BOARD SET "
					+ " BOARD_CNT = BOARD_CNT + 1 "
					+ " WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

}
