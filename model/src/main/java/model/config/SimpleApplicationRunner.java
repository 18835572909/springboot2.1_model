package model.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName:  SimpleApplicationRunner   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: renhuibo
 * @date:   2019年6月20日 下午3:44:12
 */
@Order(1)
@Component
public class SimpleApplicationRunner implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("ApplicationRunner running!");
	}

}
