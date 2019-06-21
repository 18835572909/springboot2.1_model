package model.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SimpletInterceptor extends HandlerInterceptorAdapter{

	/*
	 * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("SimpleInterceptor:在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("SimpleInterceptor:afterConcurrentHandlingStarted");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	/*
	 * 在业务处理器处理请求执行完成后，生成视图之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("SimpleInterceptor:在业务处理器处理请求执行完成后，生成视图之前执行");
		super.postHandle(request, response, handler, modelAndView);
	}

	/*
	 * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("SimpleInterceptor:在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理");
		SimpleWebUtils.setRequest(request);
		SimpleWebUtils.setResponse(response);
		
		return super.preHandle(request, response, handler);
	}

}
