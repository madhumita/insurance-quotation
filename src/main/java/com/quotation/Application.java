package com.quotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.quotation.service.impl.CriteriaInsurerImpl;

/**Boot Application class for Spring Application
 * @author Madhumita
 *
 */
@SpringBootApplication(scanBasePackages = { "com.quotation"})
@EnableCaching
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	//set cacheManager
	 @Bean(name = "postCodeCacheManager")
	    public CacheManager cacheManager() {
	        return new ConcurrentMapCacheManager("postCodeCache");
	    }
	 
	
}
