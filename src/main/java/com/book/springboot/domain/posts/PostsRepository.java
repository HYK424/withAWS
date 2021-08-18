package com.book.springboot.domain.posts;

//PostsRepository

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostsRepository extends JpaRepository<Posts,Long> { //ctril+왼클릭 으로 해당 jpa의 기능 참조할 수 있다.
    /*해당 인터페이스가 JpaRepository<>를 상속한다->해당 인터페이스는 JpaSpring의 기능들을 상속한다*/

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //JPA의 기능. 쿼리 어노테이션을 붙여-> 메소드 이름으로 쿼리 생성
    List<Posts> findAllDesc();

}