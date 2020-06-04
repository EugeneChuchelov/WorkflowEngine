package ru.psuti.workflow.data.repository;

import ru.psuti.workflow.data.entity.State;
import ru.psuti.workflow.data.entity.Workflow;

import java.util.List;

public interface WorkflowRepository {
    Workflow save(Workflow workflow);

//    Workflow update(Workflow workflow);

    void delete(String id);

    List<Workflow> findAll();

    Workflow findById(String id);

    List<Workflow> findByState(State state);
}
