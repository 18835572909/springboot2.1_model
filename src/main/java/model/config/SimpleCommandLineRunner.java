package model.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName:  SimpleCommandLineRunner   
 * @Description: 当容器装载完成后立即执行，和ApplicationRunner的区别只在于传参方式不同
 * @author: renhuibo
 * @date:   2019年6月20日 下午3:44:25
 */
@Order(2)
@Component
public class SimpleCommandLineRunner implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner running!");
	}

}
