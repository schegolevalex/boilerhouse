package com.schegolevalex.boilerhouse.pid.repositories;

import com.schegolevalex.boilerhouse.pid.models.pid.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Long> {
}
