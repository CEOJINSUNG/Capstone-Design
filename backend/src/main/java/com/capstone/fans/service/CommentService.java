package com.capstone.fans.service;


import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.domain.comment.CommentRepository;
import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.web.dto.comment.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(Long id, PostCommentDto postCommentDto, User user){
        Post post = postRepository.findById(id).orElse(null);
        if(post == null) return -1L;
        return commentRepository.save(Comment.builder()
                .comment(postCommentDto.getComment())
                .user(user)
                .post(post)
                .build()
        ).getId();
    }


    @Transactional
    public Long update(Long id, PostCommentDto postCommentDto, User user){
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null)
            return -1L;
        else if(!comment.getUser().getId().equals(user.getId()))
            return -2L;
        comment.update(postCommentDto);
        return id;
    }



    public List<Comment> findByPost(Long PostId){
        Post post = postRepository.findById(PostId).orElse(null);
        if(post == null)
            return null;
        return commentRepository.findByPost(post);
    }
}
