package ru.psuti.workflow.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.psuti.workflow.action.Action;
import ru.psuti.workflow.action.ActionType;
import ru.psuti.workflow.action.impl.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionDeserializer extends StdDeserializer<Action> {

    private static final Logger log = LoggerFactory.getLogger(ActionDeserializer.class);

    public ActionDeserializer() {
        this(null);
    }

    public ActionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Action deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("type").asText().toUpperCase();
        ActionType actionType = ActionType.valueOf(type);
        switch (actionType) {
            case REQUEST:
                return new RequestAction(node.get("url").asText());

            case WAIT: {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = null;
                try {
                    date = dateFormat.parse(node.get("until").asText());
                } catch (ParseException e) {
                    log.warn("Incorrect date", e);
                }
                return new WaitAction(date);
            }

            case MANUAL:
                return new ManualAction();

            case SCRIPT: return new ScriptAction(node.get("path").asText());

            default:
                return new NoAction();
        }
    }
}
