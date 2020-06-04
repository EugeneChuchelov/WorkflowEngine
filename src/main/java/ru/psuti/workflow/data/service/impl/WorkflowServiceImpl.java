package ru.psuti.workflow.data.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.psuti.workflow.data.entity.State;
import ru.psuti.workflow.data.entity.Task;
import ru.psuti.workflow.data.entity.Workflow;
import ru.psuti.workflow.data.repository.WorkflowRepository;
import ru.psuti.workflow.data.service.WorkflowService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    WorkflowRepository workflowRepository;

    @Override
    public Workflow save(Workflow workflow) {
        Workflow newWorkflow = new Workflow();
        Date now = new Date();
        newWorkflow.setName(workflow.getName());
        newWorkflow.setState(workflow.getState());
        newWorkflow.setTasks(workflow.getTasks());
        newWorkflow.setCreated_at(now);
        newWorkflow.setUpdated_at(now);
        newWorkflow.addCurrentTaskId(0L);
        newWorkflow.getTasks().forEach((k, task) -> {
            task.setCreated_at(now);
            task.setUpdated_at(now);
        });
        return workflowRepository.save(newWorkflow);
    }

    @Override
    public Workflow update(Workflow workflow) {
        Workflow existingWorkflow = findById(workflow.getId());
        if(existingWorkflow != null){
            BeanUtils.copyProperties(workflow, existingWorkflow);
            workflowRepository.save(workflow);
        }
        return workflow;
    }

    @Override
    public void delete(String id) {
        workflowRepository.delete(id);
    }

    @Override
    public List<Workflow> findAll() {
        return workflowRepository.findAll();
    }

    @Override
    public Workflow findById(String id) {
        return workflowRepository.findById(id);
    }

    @Override
    public Task findTask(String workflowId, long taskId) {
        Workflow workflow = workflowRepository.findById(workflowId);
        if (workflow != null) {
            return workflow.findTask(taskId);
        }
        return null;
    }

    @Override
    public List<Workflow> findActiveWorkflows() {
        List<Workflow> workflows = new LinkedList<>();

        workflows.addAll(workflowRepository.findByState(State.IDLE));
        workflows.addAll(workflowRepository.findByState(State.RUNNING));
        workflows.addAll(workflowRepository.findByState(State.ERROR));

        return workflows;
    }
}
