package com.capstone.fans.web.controller.comment;


import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.CommentService;
import com.capstone.fans.web.dto.comment.CommentDto;
import com.capstone.fans.web.dto.comment.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public Long postComment(@PathVariable Long id, @RequestBody PostCommentDto postCommentDto, @AuthenticationPrincipal User user) {
        return commentService.save(id, postCommentDto, user);
    }

    @PutMapping("/comment/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody PostCommentDto postCommentDto, @AuthenticationPrincipal User user) {
        return commentService.update(id, postCommentDto, user);
    }

    @GetMapping("/comment/{id}")
    public List<CommentDto> commentLookUp(@PathVariable Long id) {
        List<Comment> commentList = commentService.findByPost(id);
        if(commentList == null) return null;
        return commentList.stream()
                .map(c -> CommentDto.createCommentDto(c))
                .collect(Collectors.toList());
    }
}
