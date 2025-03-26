package flowfit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FlowfitApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowfitApplication.class, args);
	}

}
