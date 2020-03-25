package com.std.boot.springboot.web.dto;

import com.std.boot.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    @NotEmpty(message = "{post.title.empty}")
    private String title;

    @NotEmpty(message = "내용을 작성해주세요.")
    private String content;

    @Size(min = 2, max = 20, message = "작성자는 2~20자 내외로 작셩해주세요.")
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
