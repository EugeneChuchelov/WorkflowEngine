package ru.psuti.workflow.action.impl;

import ru.psuti.workflow.action.Action;
import ru.psuti.workflow.action.ActionType;
import ru.psuti.workflow.action.service.ManualService;
import ru.psuti.workflow.config.SpringContext;

public class ManualAction implements Action {

    private boolean isCompleted = false;

    private String workflowId;

    private long taskId;

    @Override
    public boolean perform() {
        SpringContext.getBean(ManualService.class).addManual(this);

        while (!isCompleted) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public ActionType getType() {
        return ActionType.MANUAL;
    }


    public String getWorkflowId() {
        return workflowId;
    }


    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }


    public long getTaskId() {
        return taskId;
    }


    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public void complete() {
        isCompleted = true;
    }
}
