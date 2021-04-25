package com.capstone.fans.web.controller.post;


import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.PostService;
import com.capstone.fans.web.dto.post.PostResponseDto;
import com.capstone.fans.web.dto.post.PostSaveRequestDto;
import com.capstone.fans.web.dto.post.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @PostMapping("/post/{id}")
    public Long postSave(@PathVariable Long id, @RequestBody PostSaveRequestDto postSaveRequestDto, @AuthenticationPrincipal User user) {
        return postService.save(id, postSaveRequestDto, user);
    }

    @PutMapping("/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto, @AuthenticationPrincipal User user) {
        return postService.update(id, postUpdateRequestDto, user);
    }

    @GetMapping("/post/{id}")
    public PostResponseDto postLookUp(@PathVariable Long id){
        return postService.findById(id);
    }
}
