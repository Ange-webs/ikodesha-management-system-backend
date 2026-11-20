package rw.ikodesha.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
boolean existsByRentalAgentIdAndMoveInDateAndStatusNot(UUID rentalAgentId, LocalDate moveInDate, ApplicationStatus status);

@Query("SELECT a.status, COUNT(a) FROM RentalApplication a GROUP BY a.status")
List<Object[]> countApplicationsByStatus();

@Modifying 
@Query("UPDATE RentalApplication a SET a.status = :declined WHERE a.rentalAgent.id = :rentalAgentId AND a.moveInDate = :date AND a.status <> :leased")
int declineDay(@Param("rentalAgentId") UUID rentalAgentId, 
@Param("date") LocalDate date,
 @Param("declined") ApplicationStatus declined,
  @Param("leased") ApplicationStatus leased);
}
