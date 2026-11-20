package rw.ikodesha.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.ikodesha.domain.RentPayment;
import rw.ikodesha.domain.RentalApplication;


@Repository
public interface RentPaymentRepository extends JpaRepository<RentPayment, UUID> {
    
    @Query("SELECT p FROM RentPayment p WHERE p.rentalApplication.tenant.nationalId = :nationalId ORDER BY p.paidDate DESC")
    List<RentPayment>findPaymentsOfTenant(@Param ("nationalId") String nationalId);

    boolean existsByRentalApplication(RentalApplication rentalApplication);
}
