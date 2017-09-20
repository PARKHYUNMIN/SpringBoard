package com.phm.board.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.phm.board.common.common.CommandMap;
import com.phm.board.sample.service.SampleService;

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass()); // log4j log 선언
	// Logger 객체 생성시 해당하는 클래스를 생성자의 매개변수로 넣어주었다.
	// 어떤 클래스에서 로그가 출력된 것인지 확인할 수 있게 한다.
	// 단순히 syso를 사용했다면 어떤 클래스에서 출력된 로그인지 확인하기 쉽지 않았을것 
	
	@Resource(name = "sampleService") // service 영역의 접근을 위한 선언 
	private SampleService sampleService;
	
	
	@RequestMapping(value="/sample/openBoardList.do") // 실행될 주소 
	public ModelAndView openBoardList(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/sample/boardList");
		
		// 하나의 게시글에도 여러가지 정보가 존재 글 번호, 글 제목, 작성일자 등
		// key, value 쌍으로 값을 저장하기 위해 Map 을 사용한다.
		List<Map<String, Object>> list = sampleService.selectBoardList(commandMap);
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping(value="/sample/testMapArgumentResolver.do")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		
		if(commandMap.isEmpty() == false) {
			Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String, Object> entry = null;
			while(iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/sample/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/sample/boardWrite");
	     
	    return mv;
	}
	
	@RequestMapping(value="/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
	     
	    sampleService.insertBoard(commandMap.getMap()); // insert query 실행 
	     
	    return mv; // 리다이렉트 
	}
	
	@RequestMapping(value="/sample/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/sample/boardDetail");
	     
	    Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
	    mv.addObject("map", map);
	     
	    return mv;
	}
	
	@RequestMapping(value="/sample/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("/sample/boardUpdate");
	     
	    Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
	    mv.addObject("map", map);
	     
	    return mv;
	}
	 
	@RequestMapping(value="/sample/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
	     
	    sampleService.updateBoard(commandMap.getMap());
	     
	    mv.addObject("IDX", commandMap.get("IDX"));
	    return mv;
	}
	
	@RequestMapping(value="/sample/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
	     
	    sampleService.deleteBoard(commandMap.getMap());
	     
	    return mv;
	}
}
