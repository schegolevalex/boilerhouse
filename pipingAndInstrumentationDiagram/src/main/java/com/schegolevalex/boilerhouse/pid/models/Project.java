package com.schegolevalex.boilerhouse.pid.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.schegolevalex.boilerhouse.security.models.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "projects")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonSerialize(using = ProjectSerializer.class)
//@JsonDeserialize(using = ProjectDeserializer.class, builder = Project.ProjectBuilder.class)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    User owner;

    @Column(name = "types")
    @Convert(converter = GraphConverter.class)
    Graph<ElementType, DefaultEdge> types;

    private Project(String name, User owner, Graph<ElementType, DefaultEdge> types) {
        this.name = name;
        this.owner = owner;
        this.types = types;
    }

    @JsonProperty(value = "owner")
    private String getOwnerUsername() {
        return owner.getUsername();
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

//    @JsonPOJOBuilder
    public static class ProjectBuilder {
        private Long id;
        private String name;
        private User owner;
        private Graph<ElementType, DefaultEdge> types;

        private ProjectBuilder() {
        }

        public ProjectBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProjectBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProjectBuilder withOwner(User user) {
            this.owner = user;
            return this;
        }

        public ProjectBuilder withTypes(Graph<ElementType, DefaultEdge> types) {
            this.types = types;
            return this;
        }

        public Project build() {
            if (types == null) {
                types = new Multigraph<ElementType, DefaultEdge>(DefaultEdge.class);
            }
            return new Project(name, owner, types);
        }
    }

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

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id.equals(project.id) && Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
