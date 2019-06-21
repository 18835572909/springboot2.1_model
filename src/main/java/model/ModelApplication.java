package model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import model.config.SimpleServlerListener;

/**
 * @ClassName:  ModelApplication   
 * @Description: Springboot常用模板工程 
 * @author: renhuibo
 * @date:   2019年6月20日 上午9:55:04
 */
@SpringBootApplication
@ServletComponentScan
public class ModelApplication {
	public static void main(String[] args) {
		SpringApplication.run(ModelApplication.class, args);
	}
}
