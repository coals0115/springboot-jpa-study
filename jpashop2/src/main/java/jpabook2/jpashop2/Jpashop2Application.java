package jpabook2.jpashop2;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Jpashop2Application {

	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();

		System.out.println("data = " + data);

		SpringApplication.run(Jpashop2Application.class, args);
	}

	/*JpashopApplication.java*/
	@Bean
	Hibernate5JakartaModule hibernate5Module(){
//		Hibernate5Module hibernate5Module = new Hibernate5Module();
//		hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true); // 쓰면 안됨

		return new Hibernate5JakartaModule();
	}

}
