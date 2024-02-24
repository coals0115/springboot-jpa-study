package com.fastcampus.ch2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component // PerformanceInterceptor를 AC의 Bean으로 등록
public class PerformanceInterceptor implements HandlerInterceptor { // 단일 책임 원칙(SPR) - 하나의 메서드는 하나의 책임만 갖는다.
    // 이렇게 iv를 쓰게 되면 싱글톤이기 때문에 다른 쓰레드가 변수를 변경할 수 있어서 아래와 같이 처리해주는 게 좋다.
//    long startTime; // iv - 인스턴스 변수. 싱글톤(하나의 객체)이라서 여러 쓰레드가 하나의 객체를 공유.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 전처리 작업
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime); // requset 객체에 startTime을 저장

        // handler - 요청하고 연결된 컨트롤러의 메서드
        HandlerMethod method = (HandlerMethod) handler;
        System.out.println("method.getMethod() = " + method.getMethod()); // URL하고 연결된 메서드
        System.out.println("method.getBean() = " + method.getBean()); // 메서드가 포함된 컨트롤러

        // return true; // 다음 인터셉터나 컨트롤러를 호출 false면 호출 안 함.
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 2. 후처리 작업
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        System.out.print("["+ ((HttpServletRequest)request).getRequestURI()+"]");
        System.out.println(" time="+(endTime-startTime));

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
