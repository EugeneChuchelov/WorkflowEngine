package ru.psuti.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.psuti.workflow.engine.Starter;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.psuti.workflow"})
@EnableAsync
public class WorkflowApplication {

    @Autowired
    public Starter starter;

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

    @PostConstruct
    public void init(){
        starter.init();
    }

}
