package com.grvDemo.crudDemo.service;

import com.grvDemo.crudDemo.entity.Post;
import com.grvDemo.crudDemo.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post createPost(Post post) {
        return repository.save(post);
    }
}
