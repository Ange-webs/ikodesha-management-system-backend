package rw.ikodesha.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikodesha.domain.ApplicationStatus;
import rw.ikodesha.domain.RentalApplication;
import rw.ikodesha.repository.RentalApplicationRepository;


@Service 
public class RentalApplicationService {


    @Autowired 
    private RentalApplicationRepository rentalApplicationRepo;
    public String saveRentalApplication(RentalApplication rentalApplication) {
        rentalApplicationRepo.save(rentalApplication);
        return "Rental application saved successfully";

    }

    public List<RentalApplication> getAllRentalApplications() {
        return rentalApplicationRepo.findAll();

    }

    public Optional<RentalApplication> getRentalApplicationById(UUID id) {
        return rentalApplicationRepo.findById(id);
    }

    public String updateRentalApplication(UUID id, RentalApplication rentalApplication) {
        Optional<RentalApplication> found = rentalApplicationRepo.findById(id);
        if (found.isEmpty()) {
            return "The rental application with that id does not exist";
        }

        RentalApplication existing = found.get();
        existing.setMoveInDate(rentalApplication.getMoveInDate());
        existing.setHouseCode(rentalApplication.getHouseCode());
        existing.setMonthlyRent(rentalApplication.getMonthlyRent());
        existing.setTenant(rentalApplication.getTenant());
        existing.setRentalAgent(rentalApplication.getRentalAgent());
        rentalApplicationRepo.save(existing);
        return "Rental application updated successfully";

    }

    public String deleteRentalApplication(UUID id) {
        if (!rentalApplicationRepo.existsById(id)) {
            return "The rental with that id does not exist";
        }
        rentalApplicationRepo.deleteById(id);
        return "Rental application delete successfully";
    }

    public List<RentalApplication> getRentalApplicationsByStatus(ApplicationStatus status) {
        return rentalApplicationRepo.findByStatusOrderByMoveInDateAsc(status);
    }

    public List<RentalApplication> getRentalApplicationsBetween(LocalDate start, LocalDate end) {
        return rentalApplicationRepo.findByMoveInDateBetweenOrderByMoveInDateAsc(start, end);
    }
}
