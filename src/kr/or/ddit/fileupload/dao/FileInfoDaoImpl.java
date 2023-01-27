package kr.or.ddit.fileupload.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.FileInfoVO;

public class FileInfoDaoImpl implements IFileInfoDao {
	private SqlMapClient smc;
	
	private static FileInfoDaoImpl dao;
	
	private FileInfoDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static FileInfoDaoImpl getInstance() {
		if(dao==null) dao = new FileInfoDaoImpl();
		return dao;
	}
	
	@Override
	public int insertFileinfo(FileInfoVO fileVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("fileinfo.insertFileinfo", fileVo);
			if(obj==null) cnt = 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<FileInfoVO> getAllFileInfo() {
		List<FileInfoVO> fileList = null;
		try {
			fileList = smc.queryForList("fileinfo.getAllFileinfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fileList;
	}

	@Override
	public FileInfoVO getFileinfo(int fileNo) {
		FileInfoVO fileVo = null;
		try {
			fileVo = (FileInfoVO) smc.queryForObject("fileinfo.getFileinfo", fileNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileVo;
	}

}
