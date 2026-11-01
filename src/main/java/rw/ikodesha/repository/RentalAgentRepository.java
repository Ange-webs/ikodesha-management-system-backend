package rw.ikodesha.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ikodesha.domain.RentalAgent;
@Repository
public interface RentalAgentRepository extends JpaRepository<RentalAgent, UUID> {
    
}
