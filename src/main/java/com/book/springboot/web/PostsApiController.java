package com.book.springboot.web;

import com.book.springboot.web.dto.PostsUpdateRequestDto;
import com.book.springboot.service.posts.PostsService;
import com.book.springboot.web.dto.PostsResponseDto;
import com.book.springboot.web.dto.PostsSaveRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
 private final PostsService postsService;

 /*매핑을 통해 해당 url주소를 붙여준다. 즉, /api/v1/posts를 요청하면 아래 메소드가 처리된다.*/

 @PostMapping("/api/v1/posts")  //PostMapping의 주요기능: 추가, 등록
    public Long save(@RequestBody PostsSaveRequestDto requestDto){

     return postsService.save(requestDto);
 }

 @PutMapping("/api/v1/posts/{id}")
    /*PutMapping 주요기능: 수정. {id}는 유동적이다.새로운 리소스를 생성하거나, 대상 리소스를 나타내는 데이터를 대체함. 멱등성을 갖는다*/
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
     return postsService.update(id, requestDto);
 }

 @GetMapping("/api/v1/posts/{id}")
 /*GetMapping 주요기능 : 조회, 요청. HTTP POST 메서드는 특정한 리소스를 가져오도록 요청한다. GET 요청은 데이터를 가져올 때만 사용해야 한다.*/
    public PostsResponseDto findById (@PathVariable Long id)
 {
     return postsService.findById(id);
 }

}
