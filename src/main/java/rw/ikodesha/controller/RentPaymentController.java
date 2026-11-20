package rw.ikodesha.controller;


import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ikodesha.domain.RentPayment;
import rw.ikodesha.service.RentPaymentService;



@RestController 
@RequestMapping ("/api/rent-payments")
public class RentPaymentController {

    @Autowired 
    private RentPaymentService rentPaymentService;

    @PostMapping ("/save")
    public ResponseEntity<String> saveRentPayment(@RequestParam UUID rentalApplicationId, @RequestBody RentPayment rentPayment) { 
        String msg = rentPaymentService.saveRentPayment(rentalApplicationId, rentPayment);
        if (msg.equals("Rent payment saved successfully")) {
            return  new ResponseEntity<>(msg, HttpStatus.CREATED);
        } else if (msg.equals("The rental application with that id does not exist")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
}else {
    return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
   }
 }
@GetMapping ("/all")
public ResponseEntity<List<RentPayment>> getAllRentPayments() {
    return new ResponseEntity<>(rentPaymentService.getAllRentPayments(), HttpStatus.OK);
}

@GetMapping ("/tenant/{nationalId}")
public ResponseEntity<List<RentPayment>> getPaymentsOfTenant(@PathVariable String nationalId) {
    return new ResponseEntity<>(rentPaymentService.gRentPaymentsOfTenant(nationalId), HttpStatus.OK);
}

@DeleteMapping ("/delete/{id}")
public ResponseEntity<String>deleteRentPayment(@PathVariable UUID id) {
    String msg = rentPaymentService.deleteRentPayment(id);
    if (msg.equals("Rent payment deleted successfully")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
}
