package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.request.category.CreateCategoryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Transactional
@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Test
    @DisplayName("CategoryController.findAll()")
    void findAll_test() throws Exception {
        // given
        // when
        ResultActions resultActions = mvc.perform(get("/api/categories"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.debug("테스트 : responseBody = {}", responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("code").value("R-001"));
        resultActions.andExpect(jsonPath("message").value("Good"));
    }

    @Test
    @DisplayName("컨트롤러 - 카테고리 생성 - 성공")
    void CategoryControllerTest_create_success() throws Exception {
        // given
        String name = "test_category";
        String description = "blah blah";
        CreateCategoryRequest request = new CreateCategoryRequest(name, description);
        String requestBody = om.writeValueAsString(request);
        log.info("test : requestBody = {}", requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/api/categories")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.info("test : responseBody = {}", responseBody);

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("카테고리 생성 실패 - name을 안 넣었을 때")
    void CategoryControllerTest_create_fail_1() throws Exception {
        // given
        String description = "blah blah";
        CreateCategoryRequest request = new CreateCategoryRequest(null, description);
        String requestBody = om.writeValueAsString(request);
        log.info("test : requestBody = {}", requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/api/categories")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.info("test : responseBody = {}", responseBody);

        // then
        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("errorCode").value("V-001"));
    }

    @Test
    @DisplayName("카테고리 생성 실패 - name의 길이가 100자를 넘겼을 때")
    void CategoryControllerTest_create_fail_2() throws Exception {
        // given
        String name = "In the vast expanse of the cosmos, " +
                "distant galaxies twinkle like celestial diamonds, " +
                "weaving a tapestry of cosmic wonder that captivates the imagination " +
                "and fuels the eternal quest for knowledge and understanding";
        String description = "blah blah";
        CreateCategoryRequest request = new CreateCategoryRequest(name, description);
        String requestBody = om.writeValueAsString(request);
        log.info("test : requestBody = {}", requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/api/categories")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.info("test : responseBody = {}", responseBody);

        // then
        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("data.name").value("100자 이내로 적어주세요."));
    }
}