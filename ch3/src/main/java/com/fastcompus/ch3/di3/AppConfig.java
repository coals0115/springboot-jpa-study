package com.fastcompus.ch3.di3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 객체를 생성해서 사용하기 위함이 아닌 설정을 위한 클래스
@Configuration // 일반 클래스가 아닌 설정 클래스라는 뜻
@ComponentScan // 이 클래스가 있는 패키지를 전부 뒤진다.
public class AppConfig {
//    @Bean
//    // 메서드 이름이 키로 저장..
//    Car car() { return new Car(); } // 기본은 싱글톤이기 때문에 매번 getBean 한다고 새로운 객체가 만들어지는 건 아님
//    @Bean
//    @Scope("prototype")
//    Engine engine() { return new Engine(); }
//    @Bean
//    Door door() { return new Door(); }
}
