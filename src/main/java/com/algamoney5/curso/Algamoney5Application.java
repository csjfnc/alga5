package com.algamoney5.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.algamoney5.curso.config.property.AlgaMoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(AlgaMoneyApiProperty.class)
public class Algamoney5Application {

	public static void main(String[] args) {
		SpringApplication.run(Algamoney5Application.class, args);
	}
}
