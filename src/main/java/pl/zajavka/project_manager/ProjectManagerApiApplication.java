package pl.zajavka.project_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProjectManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagerApiApplication.class, args);
	}

}
