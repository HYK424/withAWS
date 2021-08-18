package com.book.springboot.domain.posts;

//Posts

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity  //Entity : 가상 테이블을 만든다
public class Posts extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;
    private String author;

    @Builder //생성자 대신 빌더 어노테이션을 사용한다. 메소드이름.builder()를 통해 생성자보다 안전하게, 코드량을 절약하며 값을 넣을 수 있다.
    public Posts(String title, String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

}




