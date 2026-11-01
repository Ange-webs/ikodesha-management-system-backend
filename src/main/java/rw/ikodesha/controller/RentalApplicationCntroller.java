package rw.ikodesha.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import rw.ikodesha.domain.RentalApplication;
import rw.ikodesha.service.RentalApplicationService;

@RestController 
@RequestMapping("/api/rental-applications")
public class RentalApplicationCntroller {

    @Autowired
    private RentalApplicationService rentalApplicationService;

    @PostMapping("/save")
    public ResponseEntity<String> saveRentalApplication(@RequestBody RentalApplication rentalApplication) {
        String msg = rentalApplicationService.saveRentalApplication(rentalApplication);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RentalApplication>> getAllRentalApplication() {
        return new ResponseEntity<>(rentalApplicationService.getAllRentalApplications(), HttpStatus.OK);

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

 @PutMapping("/update/{id}")
 public ResponseEntity<String> updateRentalApplication(@PathVariable UUID id, @RequestBody RentalApplication rentalApplication) {
    String msg = rentalApplicationService.updateRentalApplication(id, rentalApplication);
    if (msg.equals("Rental application updated successfully")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
 }

 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> deleteRentalApplication(@PathVariable UUID id) {
     String msg = rentalApplicationService.deleteRentalApplication(id);
     if (msg.equals("Rental application deleted successfully")) {
        return new ResponseEntity<>(msg,HttpStatus.OK);
     } else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
     }
  }
} 
    

