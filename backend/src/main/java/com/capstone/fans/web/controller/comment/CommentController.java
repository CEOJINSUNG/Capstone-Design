package com.capstone.fans.web.controller.comment;


import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.service.CommentService;
import com.capstone.fans.web.dto.comment.CommentDto;
import com.capstone.fans.web.dto.comment.CommentSaveDto;
import com.capstone.fans.web.dto.comment.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/save/{id}")
    public Long postComment(@PathVariable Long id, @RequestBody CommentSaveDto commentSaveDto, @AuthenticationPrincipal User user) {
        return commentService.save(id, commentSaveDto, user);
    }

    @PutMapping("/comment/update/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentUpdateDto commentUpdateDto, @AuthenticationPrincipal User user) {
        return commentService.update(id, commentUpdateDto, user);
    }

    @GetMapping("/comment/find/{id}")
    public List<CommentDto> commentLookUp(@PathVariable Long id) {
        List<Comment> commentList = commentService.findByPost(id);
        if(commentList == null) return null;
        return commentList.stream()
                .map(c -> CommentDto.createCommentDto(c))
                .collect(Collectors.toList());
    }
}
