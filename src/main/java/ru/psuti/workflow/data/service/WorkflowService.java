package ru.psuti.workflow.data.service;

import ru.psuti.workflow.data.entity.Task;
import ru.psuti.workflow.data.entity.Workflow;

import java.util.List;

public interface WorkflowService {
    Workflow save(Workflow workflow);

    Workflow update(Workflow workflow);

    void delete(String id);

    List<Workflow> findAll();

    Workflow findById(String id);

    Task findTask(String workflowId, long taskId);

    List<Workflow> findActiveWorkflows();
}
