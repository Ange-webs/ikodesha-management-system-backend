package rw.ikodesha.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ikodesha.domain.ApplicationStatus;
import rw.ikodesha.domain.RentalApplication;
import rw.ikodesha.service.RentalApplicationService;

@RestController 
@RequestMapping("/api/rental-applications")
public class RentalApplicationController {

    @Autowired
    private RentalApplicationService rentalApplicationService;

    @PostMapping("/save")
    public ResponseEntity<String> saveRentalApplication(@RequestBody RentalApplication rentalApplication) {
        String msg = rentalApplicationService.saveRentalApplication(rentalApplication);
        if (msg.equals("Rental application saved successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.CREATED);

        }else if (msg.equals("Rental application date cannot be in the past")) {

        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<RentalApplication>> getAllRentalApplication() {
        return new ResponseEntity<>(rentalApplicationService.getAllRentalApplications(), HttpStatus.OK);

    }

    @GetMapping("/by-status")
    public ResponseEntity<List<RentalApplication>> getByStatus(@RequestParam ApplicationStatus status) {
        return new ResponseEntity<>(rentalApplicationService.getRentalApplicationsByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<RentalApplication>> getBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(rentalApplicationService.getRentalApplicationsBetween(startDate, endDate), HttpStatus.OK);
    }
    @GetMapping("/stats/by-status")
    public ResponseEntity<?> getStatsByStatus() {
        return new ResponseEntity<>(rentalApplicationService.getStatsByStatus(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalApplicationById(@PathVariable UUID id) {
Optional<RentalApplication> rentalApplication = rentalApplicationService.getRentalApplicationById(id);
if (rentalApplication.isPresent()) {
    return new ResponseEntity<>(rentalApplication.get(), HttpStatus.OK);
 } else {
    return new ResponseEntity<>("The rental application with that id does not exist", HttpStatus.NOT_FOUND);
   }
 }

 @PatchMapping("/{id}/status")
public ResponseEntity<String> changeStatus(@PathVariable UUID id, @RequestParam ApplicationStatus status) {
    String msg = rentalApplicationService.changeStatus(id, status);
if (msg.equals("Rental application status updated successfully")) {
    return new ResponseEntity<>(msg, HttpStatus.OK);
}else if (msg.equals("The rental application with that id does not exist")) {
    return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);


    } else {
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }

}

@PatchMapping ("/cancel-day")
public ResponseEntity<String> cancelDay(@RequestParam UUID rentalAgentId, @RequestParam String date) {
    String msg = rentalApplicationService.cancelDay(rentalAgentId, LocalDate.parse(date));
    return new ResponseEntity<>(msg, HttpStatus.OK);
}
   

 @PutMapping("/update/{id}")
 public ResponseEntity<String> updateRentalApplication(@PathVariable UUID id, @RequestBody RentalApplication rentalApplication) {
    String msg = rentalApplicationService.updateRentalApplication(id, rentalApplication);
    if (msg.equals("Rental application updated successfully")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
 }


  @DeleteMapping ("/delete/{id}")
  public ResponseEntity<String> deleteRentalApplication(@PathVariable UUID id) {
    String msg = rentalApplicationService.deleteRentalApplication(id);
    if (msg.equals("Rental application deleted successfully")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    } else if (msg.equals("That rental application has rent payments, delete them first")) {
        return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
    } else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
  }
} 
    

