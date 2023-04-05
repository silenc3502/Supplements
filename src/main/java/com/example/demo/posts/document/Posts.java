package com.example.demo.posts.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@NoArgsConstructor
@ToString
@Document(collection = "posts")
public class Posts {

    @Id
    private String id;
    private String title;
    private String content;
    private String writer;

    public Posts (String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
