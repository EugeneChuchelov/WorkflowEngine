package ru.psuti.workflow.engine;

import ru.psuti.workflow.data.entity.Workflow;

public interface Starter {

    void init();

    void startWorkflow(Workflow workflow);

}
