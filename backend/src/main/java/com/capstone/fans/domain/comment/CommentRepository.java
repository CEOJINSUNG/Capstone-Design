package com.capstone.fans.domain.comment;

import com.capstone.fans.domain.post.Post;
import com.capstone.fans.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
