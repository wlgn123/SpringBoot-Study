package com.std.boot.springboot.service.posts;

import com.std.boot.springboot.domain.posts.Posts;
import com.std.boot.springboot.domain.posts.PostsRepository;
import com.std.boot.springboot.web.dto.PostsListResponseDto;
import com.std.boot.springboot.web.dto.PostsResponseDto;
import com.std.boot.springboot.web.dto.PostsSaveRequestDto;
import com.std.boot.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


// final 이나 @Notnull이 붙어진 변수만 생성자로 받게 만듬
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        /**
         * JpaRepository 에서 이미 delete 메소드를 지원하고있으므로 활용.
         */
        postsRepository.delete(posts);
    }
}
