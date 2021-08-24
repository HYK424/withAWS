package com.book.springboot.config.auth;


import com.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                /*h2-console화면을 이용하기위해 해당 옵션들을 disable한다*/
                 .csrf().disable()
                 .headers().frameOptions().disable()

                .and()
                  .authorizeRequests()//URL별 권한관리를 설정하는 옵션의 시작점
                  .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                  .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //권한 관리 대사을 지정하는 옵션. URL, HTTP 메소드별 관리가능
                  .anyRequest().authenticated() //설정된 값들 외 나머지 url들
                .and()
                  .logout()
                  .logoutSuccessUrl("/") //로그아웃 기능에 대한 설정의 진입점, 로그아웃 성공 시 '/' 주소로 이동한다.
                .and()
                  .oauth2Login() //OAuth2 로그인 기능에 대한 여러 설정의 진입점
                  .userInfoEndpoint()
                  .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 위한 UserService인터페이스의 구현체를 등록한다
    }
}
