package ru.psuti.workflow.data.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.psuti.workflow.data.entity.State;
import ru.psuti.workflow.data.entity.Workflow;
import ru.psuti.workflow.data.repository.WorkflowRepository;

import java.util.List;

@Repository
public class WorkflowRepositoryImpl implements WorkflowRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Workflow save(Workflow workflow) {
        return mongoTemplate.save(workflow);
    }

    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.findAndRemove(query, Workflow.class);
    }

    @Override
    public List<Workflow> findAll() {
        return mongoTemplate.findAll(Workflow.class);
    }

    @Override
    public Workflow findById(String id) {
        return mongoTemplate.findById(id, Workflow.class);
    }

    @Override
    public List<Workflow> findByState(State state) {
        Query query = new Query(Criteria.where("state").is(state));
        return mongoTemplate.find(query, Workflow.class);
    }
}
