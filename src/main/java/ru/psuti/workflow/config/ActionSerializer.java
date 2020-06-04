package ru.psuti.workflow.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.psuti.workflow.action.Action;
import ru.psuti.workflow.action.ActionType;
import ru.psuti.workflow.action.impl.ManualAction;
import ru.psuti.workflow.action.impl.RequestAction;
import ru.psuti.workflow.action.impl.ScriptAction;
import ru.psuti.workflow.action.impl.WaitAction;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ActionSerializer extends StdSerializer<Action> {

    public ActionSerializer() {
        this(null);
    }

    public ActionSerializer(final Class<Action> t) {
        super(t);
    }

    @Override
    public void serialize(Action value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        ActionType type = value.getType();
        gen.writeStringField("type", type.toString());
        switch (type) {
            case MANUAL: {
                ManualAction manualAction = (ManualAction) value;
                gen.writeStringField("workflowId", manualAction.getWorkflowId());
                gen.writeNumberField("taskId", manualAction.getTaskId());
                break;
            }

            case WAIT: {
                WaitAction waitAction = (WaitAction) value;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                gen.writeStringField("until", dateFormat.format(waitAction.getUntil()));
                break;
            }

            case REQUEST: {
                RequestAction requestAction = (RequestAction) value;
                gen.writeStringField("url", requestAction.getUrl());
            }

            case SCRIPT:{
                ScriptAction scriptAction = (ScriptAction) value;
                gen.writeStringField("path", scriptAction.getPath());
            }

            default: {

            }
        }

        gen.writeEndObject();
    }
}
