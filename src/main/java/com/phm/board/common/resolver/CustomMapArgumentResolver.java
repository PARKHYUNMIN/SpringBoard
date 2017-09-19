package com.phm.board.common.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.phm.board.common.common.CommandMap;

public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver{

	// 파라미터와 기타 정보를 받아 실제 객체를 리턴 
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
			CommandMap commandMap = new CommandMap();
			
			HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
			Enumeration<?> enumeration = request.getParameterNames();
			
			String key = null;
			String[] values = null;
			
			// request에 담겨있는 모든 key, value를 commandMap에 저장 
			// iterator로 하나씩 가져오기 
			while(enumeration.hasMoreElements()) {
				key = (String) enumeration.nextElement();
				values = request.getParameterValues(key);
				if(values != null) {
					commandMap.put(key, (values.length > 1) ? values:values[0] );
				}
			}
			// 모든 파라미터가 담겨있는 commandMap 리턴 
			return commandMap; 
	}

	// Resolver가 적용 가능한지 검사하는 역할 
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 컨트롤러의 파라미터가 CommandMap클래스인지 검사 
		return CommandMap.class.isAssignableFrom(parameter.getParameterType());
	}

}
