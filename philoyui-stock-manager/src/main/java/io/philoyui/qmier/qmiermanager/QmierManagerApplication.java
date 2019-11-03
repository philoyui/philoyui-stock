package io.philoyui.qmier.qmiermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.com.gome.page","io.philoyui.qmier.qmiermanager"})
@EnableScheduling
public class QmierManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QmierManagerApplication.class, args);
	}

}
