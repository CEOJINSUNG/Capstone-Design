package com.capstone.fans.web.controller.post;


import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.PostService;
import com.capstone.fans.web.dto.post.PostDto;
import com.capstone.fans.web.dto.post.PostPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @PostMapping("/api/post/posts/{id}")
    public Long postPost(@PathVariable Long id, @RequestBody PostPostDto postPostDto, @AuthenticationPrincipal User user) {
        return postService.save(id, postPostDto, user);
    }

    @PutMapping("/api/post/update/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostPostDto postPostDto, @AuthenticationPrincipal User user) {
        return postService.update(id, postPostDto, user);
    }

    @GetMapping("/api/post/find/{id}")
    public PostDto postLookUp(@PathVariable Long id){
        return postService.findById(id);
    }
}
