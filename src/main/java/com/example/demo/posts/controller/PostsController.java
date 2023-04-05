package com.example.demo.posts.controller;

import com.example.demo.posts.controller.request.PostRequest;
import com.example.demo.posts.document.Posts;
import com.example.demo.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostsController {

    final private PostService postService;

    @PostMapping("/register")
    public Posts postRegister (@RequestBody PostRequest postRequest) {
        log.info("boardRegister()");

        return postService.register(postRequest);
    }
}
