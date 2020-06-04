package ru.psuti.workflow.engine;

import ru.psuti.workflow.data.entity.Workflow;

public interface WorkflowExecutor {

    void run(Workflow workflow);

}
