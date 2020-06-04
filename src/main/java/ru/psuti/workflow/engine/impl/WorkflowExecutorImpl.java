package ru.psuti.workflow.engine.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.psuti.workflow.data.entity.State;
import ru.psuti.workflow.data.entity.Task;
import ru.psuti.workflow.data.entity.Workflow;
import ru.psuti.workflow.data.service.WorkflowService;
import ru.psuti.workflow.engine.TaskExecutor;
import ru.psuti.workflow.engine.WorkflowExecutor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class WorkflowExecutorImpl implements WorkflowExecutor {

    private static final Logger log = LoggerFactory.getLogger(WorkflowExecutorImpl.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private WorkflowService workflowService;

    @Async
    @Override
    public void run(Workflow workflow) {
        log.debug("Started workflow {}: {}", workflow.getId(), workflow.getName());

        setState(workflow, State.RUNNING);

        List<Future<List<Long>>> futures = new LinkedList<>();

        for (Long id : workflow.getCurrentTaskId()) {
            futures.add(startTask(workflow, id));
        }

        boolean isFinished = false;

        while (!isFinished) {
            for (int i = 0; i < futures.size(); i++) {
                if (futures.get(i).isDone()) {
                    try {
                        if (futures.get(i).get() == null) {
                            isFinished = true;
                            break;
                        }
                        for (Long id : futures.get(i).get()) {
                            if (checkError(workflow, workflow.findTask(id))) {
                                setState(workflow, State.ERROR);
                                break;
                            }
                            if (check(workflow, workflow.findTask(id))) {
                                futures.add(startTask(workflow, id));
                            }
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                        log.error("Error while running workflow {}: {}", workflow.getId(), workflow.getName());
                    }
                    futures.remove(i--);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.debug("Finished workflow {}: {}", workflow.getId(), workflow.getName());
        setState(workflow, State.SUCCESS);
    }

    private boolean checkError(Workflow workflow, Task task) {
        for (Long id : task.getPreviousID()) {
            if (workflow.findTask(id).getState().equals(State.ERROR)) {
                workflow.addCurrentTaskId(id);
                return true;
            }
        }
        return false;
    }

    private boolean check(Workflow workflow, Task task) {
        if (task.getState().equals(State.SUCCESS)) {
            return false;
        }
        for (Long id : task.getPreviousID()) {
            if (!workflow.findTask(id).getState().equals(State.SUCCESS)) {
                return false;
            }
        }
        return true;
    }

    private Future<List<Long>> startTask(Workflow workflow, Long id) {
        return taskExecutor.run(workflow, id);
    }

    private void setState(Workflow workflow, State state) {
        workflow.setState(state);
        workflowService.update(workflow);
    }
}
