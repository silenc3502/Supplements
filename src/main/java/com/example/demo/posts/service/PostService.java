package com.example.demo.posts.service;

import com.example.demo.posts.controller.request.PostRequest;
import com.example.demo.posts.document.Posts;

public interface PostService {

    Posts register(PostRequest postRequest);
}
