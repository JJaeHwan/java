package kr.or.ddit.session;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDao {
	private SqlMapClient smc;
	
	private static MemberDao dao;
	
	private MemberDao() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static MemberDao getInstance() {
		if(dao==null) dao = new MemberDao();
		return dao;
	}
	
	public MemberVO getMember(MemberVO memVo) {
		MemberVO loginMember = null; // 반환값이 저장될 변수
		
		try {
			loginMember = (MemberVO) smc.queryForObject("member.getMember", memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginMember;
	}
	
}



















