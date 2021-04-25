package com.capstone.fans.service;


import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.domain.comment.CommentRepository;
import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.web.dto.comment.CommentDto;
import com.capstone.fans.web.dto.post.PostResponseDto;
import com.capstone.fans.web.dto.post.PostSaveRequestDto;
import com.capstone.fans.web.dto.post.PostUpdateRequestDto;
import com.capstone.fans.web.dto.user.SimpleUserInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Long save(Long id, PostSaveRequestDto postSaveRequestDto, User user) {
        /*
        return postRepository.save(
                Post.builder()
                        .user(user)
                        .club(clubRepository.findById(id).get())
                        .postType(postSaveRequestDto.getCategory())
                        .title(postSaveRequestDto.getTitle())
                        .content(postSaveRequestDto.getContents())
                        .image(postSaveRequestDto.getImages())
                        .build()
        ).getId();
         */
        return postRepository.save(postSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto postUpdateRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no matching user. id="+id));
        /*
        if(post == null)
            return -1L;
        else if(user.getId() != post.getId())
            return -2L;
         */
        post.update(postUpdateRequestDto.getPostType(), postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent(), postUpdateRequestDto.getImages());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no matching user. id="+id));
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no matching user. id="+id));
        List<Comment> commentList = commentRepository.findByPost(post);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList)
            commentDtoList.add(CommentDto.createCommentDto(comment));
        return PostResponseDto.builder()
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
