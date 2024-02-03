package com.bizplus.boardsaturday.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired CategoryController categoryController;

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

}