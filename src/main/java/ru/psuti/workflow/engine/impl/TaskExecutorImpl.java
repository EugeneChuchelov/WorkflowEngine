package ru.psuti.workflow.engine.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import ru.psuti.workflow.action.Action;
import ru.psuti.workflow.data.entity.State;
import ru.psuti.workflow.data.entity.Task;
import ru.psuti.workflow.data.entity.Workflow;
import ru.psuti.workflow.data.service.WorkflowService;
import ru.psuti.workflow.engine.TaskExecutor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class TaskExecutorImpl implements TaskExecutor {

    private static final Logger log = LoggerFactory.getLogger(TaskExecutor.class);

    @Autowired
    WorkflowService workflowService;

    private boolean makeAction(Action action) {
        return action.perform();
    }

    @Async
    @Override
    public Future<List<Long>> run(Workflow workflow, Long id) {
        Task task = workflow.findTask(id);
        task.getAction().setTaskId(id);
        task.getAction().setWorkflowId(workflow.getId());
        log.debug("Started task {} from workflow {}:{}", task.getName(), workflow.getId(), workflow.getName());
        setState(workflow, task, State.RUNNING);
        workflow.addCurrentTaskId(id);
        if (!makeAction(task.getAction())) {
            workflow.removeCurrentTaskId(id);
            setState(workflow, task, State.ERROR);
        } else {
            workflow.removeCurrentTaskId(id);
            setState(workflow, task, State.SUCCESS);
        }

        log.debug("Finished task {} from workflow {}:{}", task.getName(), workflow.getId(), workflow.getName());
        return new AsyncResult<>(task.getNextID());
    }

    private void setState(Workflow workflow, Task task, State state) {
        task.setState(state);
        Date now = new Date();
        task.setUpdated_at(now);
        workflow.setUpdated_at(now);
        workflowService.update(workflow);
    }
}
