package com.phm.board.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SampleService {
	// 비즈니스 로직을 수행할 메서드를 정의 할 인터페이스
	
	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;
	void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;
	Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;
	void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;
	void deleteBoard(Map<String, Object> map) throws Exception;
}
