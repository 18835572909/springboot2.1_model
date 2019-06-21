package model.config;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @ClassName:  SimpleApplicationListener   
 * @Description:
 * 			（1）ApplicationStartingEvent：项目刚启动时触发，此时除了注册监听器和初始器之外，其他所有处理都没有开始； 
 *			（2）ApplicationEnvironmentPreparedEvent：上下文得到环境信息之后触发，此时上下文创建还没有创建； 
 *			（3）ApplicationPreparedEvent：bean的定义信息加载完成之后触发，此时bean还没有初始化； 
 *			（4）ApplicationReadyEvent：在所有bean初始化完毕，所有回调处理完成，系统准备处理服务请求时触发； 
 * 			（5）ApplicationFailedEvent：启动过程出现异常时候触发。
 * @author: renhuibo
 * @date:   2019年6月20日 下午2:36:27
 */
public class SimpleApplicationListener implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ApplicationStartingEvent) {
			System.out.println("ApplicationStartingEvent:项目刚启动时触发，此时除了注册监听器和初始器之外，其他所有处理都没有开始； ");
		}
		
		if(event instanceof ApplicationEnvironmentPreparedEvent) {
			System.out.println("ApplicationEnvironmentPreparedEvent:上下文得到环境信息之后触发，此时上下文创建还没有创建；  ");
		}
		
		if(event instanceof ApplicationPreparedEvent) {
			System.out.println("ApplicationPreparedEvent:bean的定义信息加载完成之后触发，此时bean还没有初始化；  ");
		}

		if(event instanceof ApplicationReadyEvent) {
			System.out.println("ApplicationEnvironmentPreparedEvent:在所有bean初始化完毕，所有回调处理完成，系统准备处理服务请求时触发；   ");
		}

		if(event instanceof ApplicationFailedEvent) {
			System.out.println("ApplicationFailedEvent:启动过程出现异常时候触发。  ");
		}
		
	}
	
}
