package ru.psuti.workflow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.psuti.workflow.data.entity.Task;
import ru.psuti.workflow.data.entity.Workflow;
import ru.psuti.workflow.data.service.WorkflowService;
import ru.psuti.workflow.engine.Starter;

import java.util.List;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    private static final Logger log = LoggerFactory.getLogger(WorkflowController.class);

    @Autowired
    WorkflowService workflowService;

    @Autowired
    Starter starter;

    @PreAuthorize(RoleConstants.WRITE)
    @PostMapping
    public Workflow addWorkflow(@RequestBody Workflow workflow) {
        workflow = workflowService.save(workflow);
        starter.startWorkflow(workflow);
        log.info("Added workflow {}: {}", workflow.getId(), workflow.getName());

        return workflow;
    }

    @PreAuthorize(RoleConstants.WRITE)
    @PutMapping
    public Workflow update(@RequestBody Workflow workflow) {
        workflow = workflowService.update(workflow);
        starter.startWorkflow(workflow);
        return workflow;
    }

    @PreAuthorize(RoleConstants.WRITE)
    @DeleteMapping("/{workflowId}")
    public void delete(@PathVariable String workflowId) {
        workflowService.delete(workflowId);
    }

    @PreAuthorize(RoleConstants.READ)
    @GetMapping
    public List<Workflow> listWorkflows() {
        log.debug("Requested all workflows");
        return workflowService.findAll();
    }

    @PreAuthorize(RoleConstants.READ)
    @GetMapping("/{workflowId}")
    public Workflow getWorkflow(@PathVariable String workflowId) {
        Workflow workflow = workflowService.findById(workflowId);
        log.debug("Requested workflow {}: {}", workflow.getId(), workflow.getName());
        return workflow;
    }

    @PreAuthorize(RoleConstants.READ)
    @GetMapping("/{workflowId}/{taskId}")
    public Task getTask(@PathVariable String workflowId, @PathVariable long taskId) {
        if (log.isDebugEnabled()) {
            Workflow workflow = workflowService.findById(workflowId);
            log.debug("Requested task {}: {} from workflow {}:{}",
                    taskId, workflow.findTask(taskId).getName(), workflow.getId(), workflow.getName());
        }
        return workflowService.findTask(workflowId, taskId);
    }
}
