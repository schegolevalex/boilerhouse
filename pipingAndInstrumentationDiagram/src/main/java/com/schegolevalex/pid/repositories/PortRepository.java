package com.schegolevalex.pid.repositories;

import com.schegolevalex.pid.models.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Long> {
}
