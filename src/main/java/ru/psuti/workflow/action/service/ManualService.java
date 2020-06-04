package ru.psuti.workflow.action.service;

import ru.psuti.workflow.action.impl.ManualAction;

import java.util.Map;

public interface ManualService {
    void addManual(ManualAction action);

    void completeRequest(long id);

    Map<Long, ManualAction> getManualRequests();
}
