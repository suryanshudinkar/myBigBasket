package com.bigbasket.mybigbasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class MybigbasketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybigbasketApplication.class, args);
	}

}
