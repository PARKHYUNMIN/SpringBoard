package com.phm.board.sample.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {
	Logger log = Logger.getLogger(this.getClass()); // log4j log 선언
	// Logger 객체 생성시 해당하는 클래스를 생성자의 매개변수로 넣어주었다.
	// 어떤 클래스에서 로그가 출력된 것인지 확인할 수 있게 한다.
	// 단순히 syso를 사용했다면 어떤 클래스에서 출력된 로그인지 확인하기 쉽지 않았을것 
	
	@RequestMapping(value="/sample/openSampleList.do") // 실행될 주소 
	public ModelAndView openSampleList(Map<String, Object> commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		log.debug("interceptor testing");
		
		return mv;
	}
}
