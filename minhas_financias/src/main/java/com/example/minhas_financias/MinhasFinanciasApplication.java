package com.example.minhas_financias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
@Configuration
public class MinhasFinanciasApplication implements WebMvcConfigurer{
	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	            .allowedOrigins("http://localhost:4200") // Permita requisições apenas do frontend
	            .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
	            .allowedHeaders("*") // Headers permitidos
	            .allowCredentials(true); // Permitir envio de cookies e credenciais
	    }


	public static void main(String[] args) {
		SpringApplication.run(MinhasFinanciasApplication.class, args);
	}

}
