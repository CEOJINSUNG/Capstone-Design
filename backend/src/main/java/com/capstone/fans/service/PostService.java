package com.capstone.fans.service;


import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.domain.comment.CommentRepository;
import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.web.dto.comment.CommentDto;
import com.capstone.fans.web.dto.post.PostDto;
import com.capstone.fans.web.dto.post.PostPostDto;
import com.capstone.fans.web.dto.user.SimpleUserInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ClubRepository clubRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(Long id, PostPostDto postPostDto, User user) {
        return postRepository.save(
                Post.builder()
                        .user(user)
                        .club(clubRepository.findById(id).get())
                        .postType(postPostDto.getCategory())
                        .title(postPostDto.getTitle())
                        .content(postPostDto.getContents())
                        .image(postPostDto.getImages())
                        .build()
        ).getId();
    }

    @Transactional
    public Long update(Long id, PostPostDto postPostDto, User user) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null)
            return -1L;
        else if(user.getId() != post.getId())
            return -2L;
        post.update(postPostDto);
        return id;
    }



    public PostDto findById(Long id){
        Post post = postRepository.findById(id).orElse(null);
        List<Comment> commentList = commentRepository.findByPost(post);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList)
            commentDtoList.add(CommentDto.createCommentDto(comment));
        return PostDto.builder()
                .title(post.getTitle())
                .images(post.getImage())
                .contents(post.getContent())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .user(SimpleUserInfoDto.createSimpleUserInfoDto(post.getUser()))
                .commentDtoList(commentDtoList)
                .category(post.getPostType())
                .build();
    }







}
