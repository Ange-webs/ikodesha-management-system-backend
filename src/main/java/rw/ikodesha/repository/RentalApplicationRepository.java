package rw.ikodesha.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ikodesha.domain.ApplicationStatus;
import rw.ikodesha.domain.RentalApplication;



@Repository
public interface RentalApplicationRepository extends JpaRepository<RentalApplication, UUID> {
    
List<RentalApplication> findByStatusOrderByMoveInDateAsc(ApplicationStatus status);

List<RentalApplication> findByMoveInDateBetweenOrderByMoveInDateAsc(LocalDate start, LocalDate end);
}
