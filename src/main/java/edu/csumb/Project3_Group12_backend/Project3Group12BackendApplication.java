package edu.csumb.Project3_Group12_backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project3Group12BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(Project3Group12BackendApplication.class, args);
	}

	FBInitialize FB = new FBInitialize();
	Fullfiller fullfillerTest = new Fullfiller("Drew Clinkenbeard", 5, "Marina");

	FullfillerService service = new FullfillerService();




}


