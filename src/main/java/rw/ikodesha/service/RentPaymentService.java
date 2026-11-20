package rw.ikodesha.service;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikodesha.domain.ApplicationStatus;
import rw.ikodesha.domain.RentPayment;
import rw.ikodesha.domain.RentalApplication;
import rw.ikodesha.repository.RentPaymentRepository;
import rw.ikodesha.repository.RentalApplicationRepository;

@Service 
public class RentPaymentService {

    @Autowired 
    private RentalApplicationRepository rentalApplicationRepo;

    @Autowired 
    private RentPaymentRepository rentPaymentRepo;

    public String saveRentPayment(UUID rentalApplicationId, RentPayment rentPayment) {
      Optional<RentalApplication> found = rentalApplicationRepo.findById(rentalApplicationId);
      if (found.isEmpty()) {
        return  "The rental application with that id does not exist";
      }
      RentalApplication application = found.get();
      if (application.getStatus() != ApplicationStatus.LEASED) {
        return "Rent payments are only for LEASED rental applications";
      }
      rentPayment.setPaidDate(LocalDate.now());
      rentPayment.setRentalApplication(application);
      rentPaymentRepo.save(rentPayment);
      return "Rent payment saved successfully";
    }

    public List<RentPayment> getAllRentPayments() {
        return  rentPaymentRepo.findAll();
    }

    public List<RentPayment> gRentPaymentsOfTenant(String nationalId) {
        return rentPaymentRepo.findPaymentsOfTenant(nationalId);
    }

    public String deleteRentPayment(UUID id) {
        if (!rentPaymentRepo.existsById(id)) {
            return "The rent payment with that id does not exist";
        }
        rentPaymentRepo.deleteById(id);
        return "Rent payment deleted successfully";
    }
}