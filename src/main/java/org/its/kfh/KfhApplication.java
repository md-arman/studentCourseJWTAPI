package org.its.kfh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableJpaRepositories("org.its.kfh.repository")
//@EntityScan("org.its.kfh.model")
//@ComponentScan("org.its.kfh.service")
public class KfhApplication {

	public static void main(String[] args) {
		SpringApplication.run(KfhApplication.class, args);
	}

}
