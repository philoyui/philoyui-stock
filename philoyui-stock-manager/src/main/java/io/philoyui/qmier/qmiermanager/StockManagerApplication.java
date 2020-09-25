package io.philoyui.qmier.qmiermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.com.gome.page","io.philoyui.qmier.qmiermanager"})
@EnableScheduling
@EnableRetry
public class StockManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockManagerApplication.class, args);
	}

}
