package com.capstone.fans.web.controller.post;


import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.PostService;
import com.capstone.fans.web.dto.post.PostListDto;
import com.capstone.fans.web.dto.post.PostResponseDto;
import com.capstone.fans.web.dto.post.PostSaveRequestDto;
import com.capstone.fans.web.dto.post.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/post/save/{id}")
    public Long postSave(@PathVariable Long id, @RequestBody PostSaveRequestDto postSaveRequestDto, @AuthenticationPrincipal User user) {
        return postService.save(id, postSaveRequestDto, user);
    }

    @PutMapping("/post/update/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto postUpdateRequestDto, @AuthenticationPrincipal User user) {
        return postService.update(id, postUpdateRequestDto, user);
    }

    @GetMapping("/post/find/{id}")
    public PostResponseDto postLookUp(@PathVariable Long id){
        return postService.findById(id);
    }

    @GetMapping("/post/list/{page}/{size}")
    public List<PostListDto> getPostList(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return postService.getPostList(page, size);
    }

    @DeleteMapping("/post/delete/{post_id}")
    public Long postDelete(@PathVariable Long post_id, @AuthenticationPrincipal User user){
        return postService.delete(post_id, user);
    }
}
