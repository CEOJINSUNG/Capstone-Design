package com.capstone.fans.service;


import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.domain.comment.CommentRepository;
import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.erorrs.ErrorCodes;
import com.capstone.fans.web.dto.comment.CommentSaveDto;
import com.capstone.fans.web.dto.comment.CommentUpdateDto;
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
    public Long save(Long id, CommentSaveDto commentSaveDto, User user){
        Post post = postRepository.findById(id).orElse(null);
        if(post == null) return -1L;
        return commentRepository.save(Comment.builder()
                .comment(commentSaveDto.getComment())
                .user(user)
                .post(post)
                .build()
        ).getId();
    }


    @Transactional
    public Long update(Long id, CommentUpdateDto commentUpdateDto, User user){
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null)
            return ErrorCodes.NOT_EXIST;
        else if(!comment.getUser().getId().equals(user.getId()))
            return ErrorCodes.NOT_SAME_USER;
        comment.update(commentUpdateDto.getComment());
        return id;
    }

    @Transactional
    public Long delete(Long id, User user){
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null)
            return ErrorCodes.NOT_EXIST;
        else if(!comment.getUser().getId().equals(user.getId()))
            return ErrorCodes.NOT_SAME_USER;
        commentRepository.deleteById(id);

        return id;
    }


    public List<Comment> findByPost(Long PostId){
        Post post = postRepository.findById(PostId).orElse(null);
        if(post == null)
            return null;
        return commentRepository.findByPost(post);
    }
}
