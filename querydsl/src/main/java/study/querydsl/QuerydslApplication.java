package study.querydsl;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class QuerydslApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuerydslApplication.class, args);
	}

}
