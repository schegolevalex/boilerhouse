package com.schegolevalex.boilerhouse.pid;

import com.schegolevalex.boilerhouse.pid.models.ElementType;
import com.schegolevalex.boilerhouse.pid.models.Project;
import com.schegolevalex.boilerhouse.pid.repositories.ProjectRepository;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PipingAndInstrumentationDiagramApplication {

    @Autowired
    private ProjectRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(PipingAndInstrumentationDiagramApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        Project project1 = new Project();
        project1.setName("Котельная Прометей");

        DirectedMultigraph<ElementType, DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);

        graph.addVertex(ElementType.BOILER);
        graph.addVertex(ElementType.BALL_VALVE);
        graph.addEdge(ElementType.BOILER, ElementType.BALL_VALVE, new DefaultEdge());

        project1.setTypes(graph);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(graph.toString());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        Project save = repository.save(project1);

        System.out.println("*******************");
        System.out.println(save);
        System.out.println("*******************");
    }

}
