package kr.or.ddit.fileupload.dao;

import java.util.List;

import kr.or.ddit.vo.FileInfoVO;

public interface IFileInfoDao {
	
	/**
	 * FileInfoVO객체에 저장된 데이터를 DB에 insert하는 메서드
	 *  
	 * @param fileVo insert할 데이터가 저장된 FileInfoVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertFileinfo(FileInfoVO fileVo);
	
	/**
	 * DB에 저장된 전체 파일 목록을 가져와 List에 담아서 반환하는 메서드
	 * 
	 * @return 전체 파일 목록이 저장된 List객체
	 */
	public List<FileInfoVO> getAllFileInfo();
	
	/**
	 * 파일번호를 인수값으로 받아서 해당 파일 정보 데이터를 반환하는 메서드
	 * 
	 * @param fileNo 검색할 파일 번호
	 * @return 검색된 파일 정보 데이터가 저장된 FileInfoVO객체 
	 */
	public FileInfoVO getFileinfo(int fileNo);
	
	
	
}
