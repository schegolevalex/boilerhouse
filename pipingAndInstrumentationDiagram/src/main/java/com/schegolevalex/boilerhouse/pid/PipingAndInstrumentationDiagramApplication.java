package com.schegolevalex.boilerhouse.pid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PipingAndInstrumentationDiagramApplication {

//    @Autowired
//    private ProjectRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(PipingAndInstrumentationDiagramApplication.class, args);
    }

//    @PostConstruct
//    public void postConstruct() {
//
//        DirectedMultigraph<ElementType, DefaultEdge> graph = new DirectedMultigraph<>(DefaultEdge.class);
//        graph.addVertex(ElementType.BOILER);
//        graph.addVertex(ElementType.BALL_VALVE);
//        graph.addEdge(ElementType.BOILER, ElementType.BALL_VALVE, new DefaultEdge());
//
//        Project project1 = Project.builder().name("Котельная Прометей").types(graph).build();
//
//        Project save = repository.save(project1);
//
//        System.out.println("***************************************************");
//        System.out.println(save);
//        System.out.println("***************************************************");
//    }
}
