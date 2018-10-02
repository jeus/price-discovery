package com.b2mark.priceDiscovery;

import com.b2mark.priceDiscovery.config.PriceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@Import({ PriceConfiguration.class })
public class PriceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceDiscoveryApplication.class, args);
	}

}
