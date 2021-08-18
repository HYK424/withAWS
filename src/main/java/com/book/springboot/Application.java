package com.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
/*메인 클래스. 가장 위에서 위치하며 하위의 클래스들을 실행시킨다.*/

public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}

