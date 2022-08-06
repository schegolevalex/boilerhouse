package com.schegolevalex.boilerhouse.unit_converter.entities.relations_in_type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class Relation implements Serializable {
    @Column(name = "subtype_from")
    String subtypeFrom;

    @Column(name = "subtype_to")
    String subtypeTo;

    public Relation(String subtypeFrom, String subtypeTo) {
        this.subtypeFrom = subtypeFrom;
        this.subtypeTo = subtypeTo;
    }
}
