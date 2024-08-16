package renan.costa.eventreferral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class EventreferralApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventreferralApplication.class, args);
	}

}
