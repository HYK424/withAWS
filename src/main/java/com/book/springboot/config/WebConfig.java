package com.book.springboot.config;

import com.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;


@Override
public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(loginUserArgumentResolver);
 }
} /*LoginUserArgumentResolver가 스프링에 인식될 수 있도록 WebMvcConfigurer에 추가한다.
이때 HandlerMethodArgumentResolver은 항상 WebMvcConfigurer의 addArgumentResolvers()을 통해 추가해야만 한다.
*/
