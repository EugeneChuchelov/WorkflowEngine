package ru.psuti.workflow.data.entity;


import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode
@Document(collection = "Workflow")
public class Workflow {

    @Id
    private String id;

    private String name;

    private String description;

    private State state;

    private Date created_at;

    private Date updated_at;

    private Set<Long> currentTaskId = new HashSet<>();

    private Map<Long, Task> tasks;

    public Workflow() {
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void addCurrentTaskId(Long id) {
        currentTaskId.add(id);
    }

    public void removeCurrentTaskId(Long id) {
        currentTaskId.remove(id);
    }

    public Set<Long> getCurrentTaskId() {
        return currentTaskId;
    }

    public Map<Long, Task> getTasks() {
        return tasks;
    }

    public void setTasks(Map<Long, Task> tasks) {
        this.tasks = tasks;
    }

    public Task findTask(long id) {
        return tasks.get(id);
    }
}
