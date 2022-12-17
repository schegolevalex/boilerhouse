package com.schegolevalex.boilerhouse.pid.repositories;

import com.schegolevalex.boilerhouse.pid.models.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Long> {
}
