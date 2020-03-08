package com.std.boot.springboot.web;

import com.std.boot.springboot.config.auth.LoginUser;
import com.std.boot.springboot.config.auth.dto.SessionUser;
import com.std.boot.springboot.service.posts.PostsService;
import com.std.boot.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /**
     * 게시글 목록
     * @param model
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            System.out.println(user.toString());
            System.out.println(user.getName());
            model.addAttribute("oauthName", user.getName());
        }

        return "index";
    }

    /**
     * 게시글 작성
     */
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    /**
     * 게시글 조회
     * @param id
     * @param model
     */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
