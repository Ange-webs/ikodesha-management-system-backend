package rw.ikodesha.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ikodesha.domain.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {
    
}
