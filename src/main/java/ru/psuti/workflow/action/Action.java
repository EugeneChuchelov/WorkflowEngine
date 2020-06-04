package ru.psuti.workflow.action;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.psuti.workflow.config.ActionDeserializer;
import ru.psuti.workflow.config.ActionSerializer;

@JsonDeserialize(using = ActionDeserializer.class)
@JsonSerialize(using = ActionSerializer.class)
public interface Action {
    boolean perform();

    default ActionType getType() {
        return ActionType.NO;
    }


    default void setWorkflowId(String workflowId) {
    }


    default void setTaskId(long taskId) {
    }
}
