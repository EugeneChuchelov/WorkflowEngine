package ru.psuti.workflow.action.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.psuti.workflow.action.impl.ManualAction;
import ru.psuti.workflow.action.service.ManualService;

import java.util.HashMap;
import java.util.Map;

@Service
public class ManualServiceImpl implements ManualService {

    private static final Logger log = LoggerFactory.getLogger(ManualServiceImpl.class);
    private static long id = 0;
    private Map<Long, ManualAction> manualRequests = new HashMap<>();

    @Override
    public void addManual(ManualAction action) {
        log.debug("Added request for manual action id: {}", id);
        manualRequests.put(id++, action);
    }

    @Override
    public void completeRequest(long id) {
        log.debug("Request for manual action with id: {} completed", id);
        manualRequests.remove(id).complete();
    }

    @Override
    public Map<Long, ManualAction> getManualRequests() {
        return manualRequests;
    }
}
