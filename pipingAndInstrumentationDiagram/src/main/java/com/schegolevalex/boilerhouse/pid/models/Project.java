package com.schegolevalex.boilerhouse.pid.models;

import com.schegolevalex.boilerhouse.security.models.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    User owner;

    @Column(name = "types")
    @Convert(converter = GraphConverter.class)
    Graph<ElementType, DefaultEdge> types;

//    @Column(name = "elements")
//    @Convert(converter = GraphConverter.class)
//    Graph<Element, DefaultEdge> elements;

//    List<Port> ports;
//    ElementService elementService;
//    Context context;

//    public void addElement(ElementType type, int position) {
//        types.addVertex(type);
//    }

//    public void deleteElement(int position) {
//        elements.removeVertex(position);
//    }
}
