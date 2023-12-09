package com.example.tvmaze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TvMazeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvMazeApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/tv-maze/**")
						.allowedOrigins("https://localhost:8080")
						.allowedMethods("GET")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
