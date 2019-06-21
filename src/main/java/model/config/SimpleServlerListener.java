package model.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName:  SimpleServlerListener   
 * @Description: 常见Listener： 
 * 					ServletRequestListener、HttpSessionListener、ServletContextListener  
 * 					ServletRequestAttributeListener、HttpSessionAttributeListener、ServletContextAttributeListener
 * @author: renhuibo
 * @date:   2019年6月20日 下午3:46:19
 */
@WebListener(value="SimpleServletContext")
public class SimpleServlerListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("ServletContextListener Init!");
		ServletContextListener.super.contextInitialized(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("ServletContextListener Destroy!");
		ServletContextListener.super.contextDestroyed(sce);
	}

}
