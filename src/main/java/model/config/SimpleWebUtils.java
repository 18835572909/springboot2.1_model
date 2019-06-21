package model.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName:  WebUtils   
 * @Description: ThreadLocal: 1. 高并发保证线程安全  2. 变量范围扩大
 * @author: renhuibo
 * @date:   2019年6月20日 上午9:59:36
 */
public final class SimpleWebUtils {
	
	private final static ThreadLocal<HttpServletRequest> threadLocalRequest = new ThreadLocal<HttpServletRequest>();
	private final static ThreadLocal<HttpServletResponse> threadLocalResponse = new ThreadLocal<HttpServletResponse>();
	
	public static void setRequest(HttpServletRequest request) {
		threadLocalRequest.set(request);
	}

	public static HttpServletRequest getRequest() {
		return threadLocalRequest.get();
	}

	public static void setResponse(HttpServletResponse response) {
		threadLocalResponse.set(response);
	}

	public static HttpServletResponse getResponse() {
		return threadLocalResponse.get();
	}

	/**
	 * expiry：参数为负数代表关闭浏览器时清除cookie，参数为0时代表删除cookie，参数为正数时代表cookie存在多少秒。
	 * 
	 * @param key
	 * @param value
	 * @param expiry
	 */
	public static void setAttributeToCookies(String key, String value,
			int expiry) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		if (expiry > 0) {
			cookie.setMaxAge(expiry);
		}
		getResponse().addCookie(cookie);
	}

	public static String getAttributeFromCookies(String key) {
		Cookie[] cookies = getRequest().getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(key)) {
					return c.getValue();
				}
			}
		}
		return null;
	}

	public static void deleteCookies(String key) {
		Cookie cookie = new Cookie(key, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		getResponse().addCookie(cookie);
	}

	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		session.setMaxInactiveInterval(60 * 60);
		return session;
	}

	public static Object getAttributeFromRequest(String key) {
		return getRequest().getAttribute(key);
	}

	public static void setAttributeToRequest(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

	public static Object getAttributeFromSession(String key) {
		return getSession().getAttribute(key);
	}

	public static void setAttributeToSession(String key, Object value) {
		getSession().setAttribute(key, value);
	}
	
//	public static void writeJson(Object obj) {
//		HttpServletResponse res = getResponse();
//		res.setContentType("application/json;charset=utf-8");
//		try {
//			res.getWriter().write(JacksonUtils.toJson(obj));
//			res.getWriter().flush();
//			res.getWriter().close();
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
	
	public static String JsonReq(HttpServletRequest request) {
		BufferedReader br;
		StringBuilder sb = null;
		String reqBody = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					request.getInputStream()));
			String line = null;
			sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
			reqBody = reqBody.substring(reqBody.indexOf("{"));
			request.setAttribute("inputParam", reqBody);
			return reqBody;
		} catch (Exception e) {	
			request.setAttribute("inputParam", "");
			return "jsonerror";
		}
	}
	
//	public static JSONObject getReqJson(HttpServletRequest request){		
//		String res=(String) request.getAttribute("inputParam");
//		if(CommUtil.isEmpty(res)){
//			return null;
//		}		
//		JSONObject json=JSONObject.parseObject(res);
//		return json;
//	}
}