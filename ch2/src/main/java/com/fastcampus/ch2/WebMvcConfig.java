package com.fastcampus.ch2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 헐 이거 항상 궁금했었는데.. 쇼핑몰 플젝할 때 뭔지도 모르고 그냥 썼었어서..
@Configuration // 설정 파일이라는 걸 알려준다.
public class WebMvcConfig implements WebMvcConfigurer {// Configuration 파일은 이 인터페이스를 꼭 구현해야 한다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor()) // 아까 만든 PerformanceInterceptor 등록
                .addPathPatterns("/**") // 인터셉터 적용될 대상
                .excludePathPatterns("/css/**", "/js/**"); // 인터셉터 적용 제외대상

    }
}
