package com.digis.IHernandezProgramacionNCapas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.digis.IHernandezProgramacionNCapas")
public class IHernandezProgramacionNCapasApplication {

	public static void main(String[] args) {
		SpringApplication.run(IHernandezProgramacionNCapasApplication.class, args);
	}

}
