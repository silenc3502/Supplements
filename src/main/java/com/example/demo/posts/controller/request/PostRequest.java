package com.example.demo.posts.controller.request;

import com.example.demo.posts.document.Posts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequest {

    final private String title;
    final private String writer;
    final private String content;

    public Posts toPosts () {
        return new Posts(title, writer, content);
    }
}
