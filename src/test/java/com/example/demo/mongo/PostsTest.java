package com.example.demo.mongo;

import com.example.demo.board.entity.Board;
import com.example.demo.board.service.BoardServiceImpl;
import com.example.demo.posts.controller.request.PostRequest;
import com.example.demo.posts.document.Posts;
import com.example.demo.posts.repository.PostRepository;
import com.example.demo.posts.service.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("일반 게시판에 대한 테스트")
@SpringBootTest
public class PostsTest {

//    @Mock
//    private PostRepository mockPostRepository;
//
//    @InjectMocks
//    private PostServiceImpl mockPostService;

    @Autowired
    private PostRepository postRepository;

//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }

    @DisplayName("Post method로 게시물 작성 테스트")
    @Test
    public void 포스트_등록_테스트() {
        PostRequest postRequest = new PostRequest(
                "고", "작성자", "내용");

        Posts posts = postRequest.toPosts();
        System.out.println("posts: " + posts);

        Mono<Posts> returnedPost = postRepository.save(posts);
        Flux<Posts> postList = postRepository.findAll();
        System.out.println(postList.collectList().block());

        //doOnNext examine
        //postList.doOnNext(System.out::println).blockLast();
        System.out.println("returnedPost: " + returnedPost.block());
    }
}

