package ru.psuti.workflow.action.impl;

import ru.psuti.workflow.action.Action;
import ru.psuti.workflow.action.ActionType;

import java.io.IOException;

public class ScriptAction implements Action {
    private String path;

    public ScriptAction(String path) {
        this.path = path;
    }

    @Override
    public boolean perform() {
        int code = 0;
        try {
            if(System.getProperty("os.name").toLowerCase().contains("win")){
                code = cmd();
            } else {
                code = shell();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            code = 1;
        }
        return code == 0;
    }

    private int shell() throws IOException, InterruptedException {
        Process process = new ProcessBuilder("sh", path).start();
        return process.waitFor();
    }

    private int cmd() throws IOException, InterruptedException {
        Process process = new ProcessBuilder(path).start();
        return process.waitFor();
    }

    public String getPath() {
        return path;
    }

    @Override
    public ActionType getType() {
        return ActionType.SCRIPT;
    }
}
