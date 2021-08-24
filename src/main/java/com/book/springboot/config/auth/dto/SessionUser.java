package com.book.springboot.config.auth.dto;


import com.book.springboot.domain.user.User;
import lombok.Getter;

import javax.mail.Session;
import java.io.Serializable;

@Getter //SessionUser에는 인증된 사용자 정보만 필요하다. 그 외에 필요한 정보는 없으니 name, email, picture만 필드로 선언한다.
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private  String picture;

    public SessionUser(User user){
        this.name=user.getName();
        this.email=user.getEmail();
        this.picture=user.getPicture();
    }
}
/*User클래스를 세션에 저장하려 하면 에러가 뜬다 -> User클래스에서 직렬화를 구현하지 않았기 때문.
하지만 User클래스는 엔티티기 때문에 직렬화를 시킬 수 없다. 엔티티 클래스가 언제 다른 엔티티 클래스와 관계가 형성될 지 모르기 때문.
-> 직렬화 기능을 가진 세션dto를 추가로 만들었다. 이 편이 유지보수에 유리하다*/