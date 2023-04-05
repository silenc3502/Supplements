package com.example.demo.posts.service;

import com.example.demo.posts.controller.request.PostRequest;
import com.example.demo.posts.document.Posts;
import com.example.demo.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    final private PostRepository postRepository;

    @Override
    public Posts register(PostRequest postRequest) {
        Posts posts = postRequest.toPosts();
        postRepository.save(posts);

        log.info("Posts: " + posts);

        return posts;
    }
}
