package space.artexplorer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

	}

//	@Bean
//	CommandLineRunner commandLineRunner(
//			CategoryRepository categoryRepository,
//			LaboratoryRepository laboratoryRepository
//	) {
//		return args -> {
//
//			Category category = new Category("Acquerello");
//
//			Laboratory laboratory = new Laboratory("Ciao", "Ciao, mondo");
//			category.getLaboratories().add(laboratory);
//			categoryRepository.save(category);
//
//		};
//	}

}
