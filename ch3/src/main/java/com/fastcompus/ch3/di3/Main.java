package com.fastcompus.ch3.di3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
class Car {
    Engine engine;
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

@Component
class Engine {}

@Component
class Door {}

public class Main {
    public static void main(String[] args) {
        // AC를 생성하면서 설정 파일에 있는 객체를 빈으로 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

//        Car car = (Car) ac.getBean("car"); // byName 객체(빈)을 조회
//        Car car = ac.getBean("car", com.fastcompus.ch3.di3.Car.class); // 위와 동일
//        Engine engine = ac. getBean (Engine.class) ;
//        Engine engine2 = ac. getBean (Engine.class);
//        Engine engine3 = ac. getBean (Engine.class);
//
//        System.out.println("car = " + car);
//        System.out.println("engine = " + engine);
//        System.out.println("engine2 = " + engine2);
//        System.out.println("engine3 = " + engine3);
//
//        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount()); // Bean이 몇 개 등록되어있는지
//        System.out.println("Arrays.toString(ac.getBeanDefinitionNames()) = " + Arrays.toString(ac.getBeanDefinitionNames()));
//        System.out.println("ac.containsBeanDefinition(\"engine\") = " + ac.containsBeanDefinition("engine"));
//        System.out.println("ac.isSingleton(\"engine\") = " + ac.isSingleton("engine"));

        SysInfo info = ac.getBean(SysInfo.class);
        System.out.println("info = " + info);

        Map<String, String> env = System.getenv(); // 시스템 환경설정 파일을 다 확인 가능
        System.out.println("env = " + env);
    }
}
