package by.gulis.project3API;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project3ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project3ApiApplication.class, args);
	}
	@Bean
	public ModelMapper createModelMapper(){
		return new ModelMapper();
	}

}
