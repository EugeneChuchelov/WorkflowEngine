package ru.psuti.workflow.data.service;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.psuti.workflow.WorkflowApplication;
import ru.psuti.workflow.data.entity.State;
import ru.psuti.workflow.data.entity.Task;
import ru.psuti.workflow.data.entity.Workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
class WorkflowServiceTest {

    @Autowired
    private WorkflowService workflowService;

    private static Workflow workflow;

    @BeforeAll
    public static void init(){
        Task task1 = new Task();
        task1.setName("task1");
        task1.setState(State.IDLE);

        Task task2 = new Task();
        task1.setName("task2");
        task1.setState(State.IDLE);

        workflow = new Workflow();
        Map<Long, Task> taskMap = new HashMap<>();
        taskMap.put(0L, task1);
        taskMap.put(1L, task2);
        workflow.setTasks(taskMap);
        workflow.setName("worktest");
        workflow.setState(State.IDLE);
    }

    @BeforeEach
    public void setUp() {
        workflowService.save(workflow);
    }

    @AfterEach
    public void tearDown() {
        List<Workflow> workflows = workflowService.findAll();
        if(!workflows.isEmpty())
            workflowService.delete(workflows.get(0).getId());
    }

    @Test
    public void addWorkflow() {
        Assert.assertNotNull(workflowService.save(workflow));
    }

    @Test
    public void updateWorkflow() {
        String newName = "OtherWorkflow";
        Workflow workflow = workflowService.findAll().get(0);
        workflow.setName(newName);
        Assert.assertEquals(workflowService.update(workflow).getName(), newName);
    }

    @Test
    public void deleteWorkflow() {
        String id = workflowService.findAll().get(0).getId();
        workflowService.delete(id);
    }

    @Test
    public void getAllWorkflows() {
        Assert.assertTrue(!workflowService.findAll().isEmpty());
    }

    @Test
    public void getWorkflow() {
        String id = workflowService.findAll().get(0).getId();
        Assert.assertNotNull(workflowService.findById(id));
    }

    @Test
    public void getTask() {
        String id = workflowService.findAll().get(0).getId();
        Assert.assertNotNull(workflowService.findTask(id, 0L));
    }

    @Test
    public void getActiveWorkflows() {
        Assert.assertTrue(!workflowService.findActiveWorkflows().isEmpty());
    }
}