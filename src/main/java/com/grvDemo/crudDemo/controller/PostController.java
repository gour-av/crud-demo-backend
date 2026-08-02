package com.grvDemo.crudDemo.controller;

import com.grvDemo.crudDemo.entity.Post;
import com.grvDemo.crudDemo.service.PostService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> getPosts() {
        return service.getAllPosts();
    }

    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return service.createPost(post);
    }
}
