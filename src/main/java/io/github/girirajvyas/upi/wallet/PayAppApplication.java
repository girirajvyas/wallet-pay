package io.github.girirajvyas.upi.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayAppApplication.class, args);
	}

}
