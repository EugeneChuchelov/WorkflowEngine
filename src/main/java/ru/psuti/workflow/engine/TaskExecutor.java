package ru.psuti.workflow.engine;

import ru.psuti.workflow.data.entity.Workflow;

import java.util.List;
import java.util.concurrent.Future;

public interface TaskExecutor {

    Future<List<Long>> run(Workflow workflow, Long id);

}
