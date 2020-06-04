package ru.psuti.workflow.action.impl;

import ru.psuti.workflow.action.Action;
import ru.psuti.workflow.action.ActionType;

import java.util.Date;

public class WaitAction implements Action {
    private Date until;

    public WaitAction(Date until) {
        this.until = until;
    }

    public Date getUntil() {
        return until;
    }

    @Override
    public boolean perform() {
        long time = until.getTime();
        long now;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            now = System.currentTimeMillis();
        } while (now < time);
        return true;
    }

    @Override
    public ActionType getType() {
        return ActionType.WAIT;
    }
}
