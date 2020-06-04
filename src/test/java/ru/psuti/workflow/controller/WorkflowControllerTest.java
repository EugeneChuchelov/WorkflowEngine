package ru.psuti.workflow.controller;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;
import ru.psuti.workflow.WorkflowApplication;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
@WebAppConfiguration
class WorkflowControllerTest {
/*
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    @Autowired
    private ResourceLoader resourceLoader;
/*
    @BeforeAll
    public static void init() throws Exception {
        mockMvc =
    }
*/
    /*
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addWorkflow() throws Exception {
        String workflowJson = readFileToString("classpath:workflow.json");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/workflow")
                .content(workflowJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        Assert.assertEquals(200, status);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void listWorkflows() {
    }

    @Test
    void getWorkflow() {
    }

    @Test
    void getTask() {
    }

    public static String readFileToString(String path) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);
        return asString(resource);
    }

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    */
}