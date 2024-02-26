package com.fastcompus.ch3.di4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.Arrays;

class Car {
    @Override
    public String toString() {
        return "Car{}";
    }
}

class SportsCar extends Car {
    @Override
    public String toString() {
        return "SportsCar{}";
    }
}

class SportsCar2 extends Car {
    @Override
    public String toString() {
        return "SportsCar2{}";
    }
}

@Component
@Conditional(TrueCondition.class)
class Engine {
    @Override
    public String toString() {
        return "Engine{}";
    }
}

@Component
@Conditional(OSCondition.class)
class Door {
    @Override
    public String toString() {
        return "Door{}";
    }
}

//@SpringBootConfiguration // 은 아래의 3개 애너테이션을 붙인 것과 동일
 //@SpringBootConfiguration // @Configuration과 동일
// ===========================

class TrueCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {


        return true;
    }
}

class OSCondition implements Condition {
    // metadata = Engine 클래스에 대한 정보
    // context =
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
//        System.out.println("System.getProperties() = " + System.getProperties()); // 어떤 값들을 가지고 올 수 있는지 알 수 있음
        System.out.println("env.getProperty(\"os.name\") = " + env.getProperty("os.name"));

//        return env.getProperty("os.name").equals("Mac OS X");
//        return env.getProperty("os.name").equals("windows");
        return env.getProperty("mode").equals("test");
    }
}

@Configuration
@Import({Config1.class, Config2.class}) // 설정파일을 다 적어주지 않고 MainConfig에서 Import 해줘도 됨
//@Import(MyImportSelector.class)
class MainConfig { @Bean Car car() { return new Car(); } }
class Config1 { @Bean Car sportsCar() { return new SportsCar(); } }
class Config2 { @Bean Car sportsCar() { return new SportsCar2(); } }

class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) { // 이 애너테이션이 붙은 클래스에 대한 정보
        return new String[]{Config1.class.getName()};
    }
}

@EnableConfigurationProperties(MyProperties.class)
@Configuration
//@EnableAutoConfiguration
@ComponentScan
public class Main {
    public static void main(String[] args) {
//        ApplicationContext ac = SpringApplication.run(Main.class, args); // 여기서는 설정 파일 하나만 지정 가능
//        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class, Config1.class, Config2.class); // 자바 설정을 이용하는 AC. 얘는 설정 파일 여러 개 지정이 가능하다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class); // 자바 설정을 이용하는 AC. 얘는 설정 파일 여러 개 지정이 가능하다.
        System.out.println("ac = " + ac);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames); // 빈 목록이 담긴 배열을 정렬
        Arrays.stream(beanDefinitionNames) // 배열을 스트림으로 변환
                .filter(b -> !b.startsWith("org")) // org로 시작하는 이름을 제외
                .forEach(System.out::println); // 스트림의 요소를 하나씩 꺼내서 출력

        System.out.println("ac.getBean(\"sportsCar\") = " + ac.getBean("sportsCar")); // 왜냐면 순서대로 등록을 하기 때문에 앞에서 읽은 걸 뒤에서 엎어침.

        MyProperties prop = ac.getBean(MyProperties.class);
        System.out.println("prop.getDomain() = " + prop.getDomain());
        System.out.println("prop.getEmail() = " + prop.getEmail());

    }

    @Bean // 이 자체가 설정 파일이기 때문에 빈이 AC에 등록된다.
    MyBean myBean() { return new MyBean(); }

}

class MyBean {}