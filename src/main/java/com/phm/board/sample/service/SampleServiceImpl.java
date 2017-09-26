package com.phm.board.sample.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.phm.board.common.logger.LoggerInterceptor;
import com.phm.board.common.util.FileUtils;
import com.phm.board.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService {
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;
	
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		return sampleDAO.selectBoardList(map);
	}


	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		sampleDAO.insertBoard(map);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size; i++) {
			sampleDAO.insertFile(list.get(i));
		}
		
	}


	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		sampleDAO.updateHitCnt(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> tempMap = sampleDAO.selectBoardDetail(map);
		resultMap.put("map", tempMap);
		
		List<Map<String, Object>> list = sampleDAO.selectFileList(map);
		resultMap.put("list", list);
		
	    return resultMap;
	}


	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		sampleDAO.updateBoard(map); // 글의 내용을 수정하는 메서드 
		
		sampleDAO.deleteFileList(map); // 해당 게시글의 첨부파일을 전부 삭제처리를 하는 역할 (DEL_GB = 'Y')
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(map, request); // request에 있는 파일정보를 list로 변환 
		Map<String, Object> tempMap = null;
		for(int i=0, size=list.size(); i<size; i++) { // 파일을 하나씩 입력, 수정 (구분자 IS_NEW를 통한 구분)
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) { // 신규 저장될 파일 
				sampleDAO.insertFile(tempMap);
			} else { // 기존에 저장되어 있던 파일 
				sampleDAO.updateFile(tempMap);
			}
		}
	}
	
	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
	    sampleDAO.deleteBoard(map);
	}
}
