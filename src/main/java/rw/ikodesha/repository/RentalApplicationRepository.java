package rw.ikodesha.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.ikodesha.domain.ApplicationStatus;
import rw.ikodesha.domain.RentalApplication;
import rw.ikodesha.domain.Tenant;



@Repository
public interface RentalApplicationRepository extends JpaRepository<RentalApplication, UUID> {
    
List<RentalApplication> findByStatusOrderByMoveInDateAsc(ApplicationStatus status);

List<RentalApplication> findByMoveInDateBetweenOrderByMoveInDateAsc(LocalDate start, LocalDate end);

@Query ("SELECT DISTINCT a.tenant FROM RentalApplication a WHERE a.rentalAgent.id = :rentalAgentId")
List<Tenant> findTenantsOfRentalAgent(@Param("rentalAgentId") UUID rentalAgentId);
}
