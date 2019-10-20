package io.philoyui.qmier.qmiermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.com.gome.page","io.philoyui.qmier.qmiermanager"})
public class QmierManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QmierManagerApplication.class, args);
	}

}
