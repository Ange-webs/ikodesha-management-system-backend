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
        if (rentalApplication.getMoveInDate().isBefore(LocalDate.now())) {
            return "Rental application date cannot be in the past";

        }
        boolean booked = rentalApplicationRepo.existsByRentalAgentIdAndMoveInDateAndStatusNot(
            rentalApplication.getRentalAgent().getId(),
            rentalApplication.getMoveInDate(),
            ApplicationStatus.DECLINED);
        if (booked) {
            return "Rental agent already has a move-in on that date";

        }
    rentalApplication.setStatus(ApplicationStatus.SUBMITTED);
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

    public String changeStatus(UUID id, ApplicationStatus newStatus) {
        Optional<RentalApplication> found =rentalApplicationRepo.findById(id);
        if (found.isEmpty()) {
            return "The rental application with that id does not exist";
        }
        RentalApplication application = found.get();
        ApplicationStatus current = application.getStatus();
        boolean allowed = false;
        if (current == ApplicationStatus.SUBMITTED) {
            allowed = newStatus ==ApplicationStatus.UNDER_REVIEW || newStatus == ApplicationStatus.DECLINED;
        } else if (current == ApplicationStatus.UNDER_REVIEW) {
            allowed = newStatus ==ApplicationStatus.LEASED || newStatus == ApplicationStatus.DECLINED;
        }
        if (!allowed) {
            return "Cannot change status from " + current +" to " + newStatus;
        }
        application.setStatus(newStatus);
        rentalApplicationRepo.save(application);
        return "Rental application status updated successfully";
    }
}
