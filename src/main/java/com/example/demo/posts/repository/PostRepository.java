package com.example.demo.posts.repository;

import com.example.demo.posts.document.Posts;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Posts, Long> {
}
