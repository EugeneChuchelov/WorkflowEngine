package ru.psuti.workflow.data.entity;

import lombok.EqualsAndHashCode;
import ru.psuti.workflow.action.Action;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode
public class Task {

    private String name;

    private String description;

    private Date created_at;

    private Date updated_at;

    private State state;

    private List<Long> nextID;

    private List<Long> previousID;

    private Action action;

    public Task() {
    }

    public Action getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Long> getNextID() {
        return nextID;
    }

    public void setNextID(List<Long> nextID) {
        this.nextID = nextID;
    }

    public List<Long> getPreviousID() {
        return previousID;
    }
}
