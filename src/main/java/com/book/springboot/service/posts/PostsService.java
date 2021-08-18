package com.book.springboot.service.posts;

import com.book.springboot.web.dto.PostsListResponseDto;
import com.book.springboot.web.dto.PostsUpdateRequestDto;
import com.book.springboot.domain.posts.PostsRepository;
import com.book.springboot.web.dto.PostsResponseDto;
import com.book.springboot.web.dto.PostsSaveRequestDto;
import com.book.springboot.domain.posts.Posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class PostsService {
    private final PostsRepository postsRepository; //인터페이스 PostsRepository의 참조변수

    @Transactional //@Transactional은 해당 클래스, 메소드가 트랜잭션 처리를 하도록 한다. 데이터 베이스의 상태를 변경하는 작업-트랜잭션의 4성질 참조
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts=postsRepository.findById(id)  //PostsRepository는 SpringJPA를 상속한다. 해당 메소드는 SpringJPA에서 지원하는 기능이다.
                .orElseThrow(() ->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()  //postsrepository에서 정의했던 findAllDesc()사용한다.
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }


    public PostsResponseDto findById (Long id){
        Posts entity=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);

    }
}
