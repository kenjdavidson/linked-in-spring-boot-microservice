package kjd.linkedin.explorecali;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kjd.linkedin.explorecali.initializer.DataInitializer;

@SpringBootApplication
public class ExploreCaliApplication implements ApplicationRunner {	

	@Autowired DataInitializer dataInitializer;

	public static void main(String[] args) {
		SpringApplication.run(ExploreCaliApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		dataInitializer.initialize();
	}

}
