package model.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * @ClassName:  SimpleFilter   
 * @Description: 拦截器
 * @author: renhuibo
 * @date:   2019年6月20日 下午3:45:33
 */
@WebFilter(filterName="simpleFilter",urlPatterns="/*")
public class SimpleFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		System.out.println("Filter:"+httpRequest.getRequestURI());
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String filterName = filterConfig.getFilterName();
		System.out.println(filterName+",Filter init!");
	}

	@Override
	public void destroy() {
		System.out.println("Filter destroy!");
	}

}
