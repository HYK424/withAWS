package com.book.springboot.web;

import com.book.springboot.config.auth.dto.SessionUser;
import com.book.springboot.service.posts.PostsService;
import com.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller

public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/") //index.mustache에 url매핑. "/"를 요청하면 index가 나온다
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        /*Model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장하는데, 여기서는 postsService.findAllDesc()로 가져온 결과를
        posts형식으로 index.mustache에 전달한다.*/
SessionUser user=(SessionUser) httpSession.getAttribute("user");
if(user!=null){
    model.addAttribute("userName", user.getName());
}
        return "index";
        /*머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자명은 자동으로 지정된 상태로 파일을 찾는다.
       즉, 컨트롤러 단에서 index이름을 쓰는 파일을 찾아서 호출하는 셈이다. 앞의 경로-resource/templates-에서 .mustache파일을 찾는 것.*/
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save"; //위와 같이 posts-save 파일을 찾아온다.
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto=postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
