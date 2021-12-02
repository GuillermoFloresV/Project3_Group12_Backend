package edu.csumb.Project3_Group12_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class Project3Group12BackendApplicationTests {

	@Test
	void contextLoads() {
	}
	FBInitialize FB = new FBInitialize();
	Fullfiller fullfillerTest = new Fullfiller("Drew Clinkenbeard", 5, "Marina");

	FullfillerService service = new FullfillerService();

}
