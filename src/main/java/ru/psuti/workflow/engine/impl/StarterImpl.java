package ru.psuti.workflow.engine.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.psuti.workflow.data.entity.Workflow;
import ru.psuti.workflow.data.service.WorkflowService;
import ru.psuti.workflow.engine.Starter;
import ru.psuti.workflow.engine.WorkflowExecutor;

import java.util.List;

@Service
public class StarterImpl implements Starter {

    private static final Logger log = LoggerFactory.getLogger(StarterImpl.class);

    @Autowired
    WorkflowExecutor workflowExecutor;

    @Autowired
    WorkflowService workflowService;

    @Override
    public void init() {
        List<Workflow> workflows = workflowService.findActiveWorkflows();
        for (Workflow workflow : workflows) {
            startWorkflow(workflow);
        }
    }

    @Override
    public void startWorkflow(Workflow workflow) {
        workflowExecutor.run(workflow);
    }
}
