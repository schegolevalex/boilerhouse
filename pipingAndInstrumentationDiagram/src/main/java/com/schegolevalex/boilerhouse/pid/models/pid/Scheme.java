package com.schegolevalex.boilerhouse.pid.models.pid;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Scheme {
    DirectedMultigraph<ElementType, DefaultEdge> types;
    DirectedMultigraph<Element, DefaultEdge> elements;
//    List<Port> ports;
//    ElementService elementService;
//    Context context;

    public void addElement(ElementType type, int position) {
        types.addVertex(type);

    }

    public void deleteElement(int position) {
//        elements.removeVertex(position);
    }


}
