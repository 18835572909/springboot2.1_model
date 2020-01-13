package com.tuodi.web;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**   
 * @author: renhuibo
 * @date:   2020年1月13日 上午11:40:13  
 * @Description:  
 */
@Slf4j
public class BooleanHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	/* (non-Javadoc)
	 * @see org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("name: "+parameter.getParameterType().getName());
		return StringUtils.equalsIgnoreCase(parameter.getParameterType().getName(), "boolean") || StringUtils.equalsIgnoreCase(parameter.getParameterType().getName(), "java.lang.Boolean");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		String paramName = parameter.getParameter().getName();
		Object attribute = webRequest.getAttribute(paramName, NativeWebRequest.SCOPE_REQUEST);
		if(attribute==null){
			return false;
		}else{
			return attribute;
		}
	}

}
