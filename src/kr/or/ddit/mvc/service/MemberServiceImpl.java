package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	// 1번
	private static MemberServiceImpl service;
	
	private IMemberDao dao;
	
	// 2번
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	// 3번
	public static MemberServiceImpl getInstance() {
		if(service == null) service = new MemberServiceImpl();
		return service;
	}
	
	
	
//	public MemberServiceImpl() {
////		dao = new MemberDaoImpl();	// DAO객체를 생성해서 dao객체변수를 초기화한다.
//		dao = MemberDaoImpl.getInstance();	// DAO객체를 생성해서 dao객체변수를 초기화한다.
//	}

	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		
		return dao.updateMember2(paramMap);
	}

}
