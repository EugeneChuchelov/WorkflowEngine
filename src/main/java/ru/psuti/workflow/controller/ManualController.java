package ru.psuti.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psuti.workflow.action.impl.ManualAction;
import ru.psuti.workflow.action.service.ManualService;

import java.util.Map;

@RestController
@RequestMapping("/manual")
public class ManualController {

    @Autowired
    ManualService manualService;

    @PreAuthorize(RoleConstants.MANUAL)
    @GetMapping
    public Map<Long, ManualAction> getManualRequests() {
        return manualService.getManualRequests();
    }

    @PreAuthorize(RoleConstants.MANUAL)
    @GetMapping(path = "/complete/{id}")
    public void completeManualRequest(@PathVariable long id) {
        manualService.completeRequest(id);
    }
}
