package com.grvDemo.crudDemo.repository;

import com.grvDemo.crudDemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
