package com.capstone.fans.service;


import com.capstone.fans.domain.comment.Comment;
import com.capstone.fans.domain.comment.CommentRepository;
import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.post.PostRepository;
import com.capstone.fans.domain.user.User;
import com.capstone.fans.domain.user.club.Club;
import com.capstone.fans.domain.user.club.ClubRepository;
import com.capstone.fans.erorrs.ErrorCodes;
import com.capstone.fans.web.dto.comment.CommentDto;
import com.capstone.fans.web.dto.post.PostListDto;
import com.capstone.fans.web.dto.post.PostResponseDto;
import com.capstone.fans.web.dto.post.PostSaveRequestDto;
import com.capstone.fans.web.dto.post.PostUpdateRequestDto;
import com.capstone.fans.web.dto.user.SimpleUserInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ClubRepository clubRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long save(Long id, PostSaveRequestDto postSaveRequestDto, User user) {
        Club club = clubRepository.findById(id).orElse(null);
        if(club == null){
            //return ErrorCodes.NOT_EXIST;
            club = clubRepository.findAll().get(0);
        }

        return postRepository.save(
                Post.builder()
                        .user(user)
                        .club(club)
                        .postType(postSaveRequestDto.getCategory())
                        .title(postSaveRequestDto.getTitle())
                        .content(postSaveRequestDto.getContents())
                        .image(postSaveRequestDto.getImages())
                        .build()
        ).getId();

    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto postUpdateRequestDto, User user) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null)
            return ErrorCodes.NOT_EXIST;
        else if(!user.getId().equals(post.getUser().getId()))
            return ErrorCodes.NOT_SAME_USER;
        post.update(postUpdateRequestDto.getPostType(), postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent(), postUpdateRequestDto.getImages());
        return id;
    }

    @Transactional
    public Long delete(Long id, User user){
        Post post = postRepository.findById(id).orElse(null);
        if(post == null)
            return ErrorCodes.NOT_EXIST;
        else if(!user.getId().equals(post.getUser().getId()))
            return ErrorCodes.NOT_SAME_USER;
        postRepository.delete(post);
        return id;
    }



    @Transactional(readOnly = true)
    public List<PostListDto> getPostList(Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return postRepository.findAll(pageRequest).get()
                .map(p->PostListDto.builder()
                        .id(p.getId())
                        .image(p.getImage().size()==0? null : p.getImage().get(0))
                        .title(p.getTitle())
                        .build()
                ).collect(Collectors.toList());
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
