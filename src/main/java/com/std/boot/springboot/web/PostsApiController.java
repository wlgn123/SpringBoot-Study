package com.std.boot.springboot.web;

import com.std.boot.springboot.service.posts.PostsService;
import com.std.boot.springboot.web.dto.PostsResponseDto;
import com.std.boot.springboot.web.dto.PostsSaveRequestDto;
import com.std.boot.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /**
     * 게시글 저장
     * @param requestDto
     */
    @PostMapping("/api/v1/posts")
    public Long save (@Valid @RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }

    /**
     * 게시글 수정
     * @param id
     * @param requestDto
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update (@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    /**
     * 게시글 조회
     * @param id
     */
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
