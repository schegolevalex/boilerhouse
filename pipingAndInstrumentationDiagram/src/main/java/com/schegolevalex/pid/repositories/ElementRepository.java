package com.schegolevalex.pid.repositories;

import com.schegolevalex.pid.models.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Long> {
}
