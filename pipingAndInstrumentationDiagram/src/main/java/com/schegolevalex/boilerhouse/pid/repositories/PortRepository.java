package com.schegolevalex.boilerhouse.pid.repositories;

import com.schegolevalex.boilerhouse.pid.models.pid.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Long> {
}
