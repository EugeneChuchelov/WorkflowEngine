package ru.psuti.workflow.action.impl;

import ru.psuti.workflow.action.Action;

public class NoAction implements Action {
    @Override
    public boolean perform() {
        return true;
    }
}
