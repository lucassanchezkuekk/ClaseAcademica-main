package com.eduTech.unitarias1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.eduTech.unitarias1", "com.eduTech.controller"})
public class GestionEnvioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEnvioApplication.class, args);
	}

}
