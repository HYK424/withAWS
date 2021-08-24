package com.book.springboot.domain.user;


import com.book.springboot.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) /*JPA로 데이터베이스에 저장할 때 Enum값을 어떻게 저장할 지 결정한다. 기본적으로 int로 저장되도록
    세팅되어 있는 것을 (EnumTye.STRING)을 통해 무낮열로 데이터베이스에 저장되도록 한다*/
    @Column(nullable = false)
    private Role role; //Role은 Enum(열거형)이다

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name=name;
        this.email=email;
        this.picture=picture;
        this.role=role;

    }

    public User update(String name, String picture){
        this.name=name;
        this.picture=picture;

        return this;
    }

    public String getRoleKey(){

        return this.role.getKey();
    }
}
