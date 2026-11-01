package rw.ikodesha.controller;


import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ikodesha.domain.RentalAgent;
import rw.ikodesha.service.RentalAgentService;

@RestController
@RequestMapping("/api/rental-agents")
public class RentalAgentController {


    @Autowired
    private RentalAgentService rentalAgentService;

    
    @PostMapping("/save")
    public ResponseEntity<String> saveRentalAgent(@RequestBody RentalAgent rentalAgent) {
        String msg = rentalAgentService.saveRentalAgent(rentalAgent);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalAgentById(@PathVariable UUID id) {
        Optional<RentalAgent> rentalAgent = rentalAgentService.getRentalAgentById(id);
        if (rentalAgent.isPresent()) {
            return new ResponseEntity<>(rentalAgent.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("The rental agent with that does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRentalAgent(@PathVariable UUID id, @RequestBody RentalAgent rentalAgent) {
        String msg = rentalAgentService.updateRentalAgent(id, rentalAgent);
        if (msg.equals("Rental agent updated successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        
        } else {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRentalAgent(@PathVariable UUID id) {
        String msg = rentalAgentService.deleteRentalAgent(id);
        if (msg.equals("Rental agent deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

        }
    }
}
