package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.application.response.post.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostQueryTest {

    @Autowired
    PostQuery postQuery;

    @Test
    void findAll_test() throws Exception {
        List<PostResponse> all = postQuery.findAll();

        for (PostResponse postResponse : all) {
            System.out.println(postResponse);
        }
    }

}