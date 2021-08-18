package com.book.springboot.web;

import com.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller

public class IndexController {
    private final PostsService postsService;

    @GetMapping("/") //index.mustache에 url매핑. "/"를 요청하면 index가 나온다
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    } //컨트롤러 단에서 index이름을 쓰는 파일을 자동으로 찾아서 호출. resource/templates디렉토리 먼저 찾음


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save"; //위와 같이 posts-save 파일을 찾아온다.
    }

}
