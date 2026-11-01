package rw.ikodesha.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikodesha.domain.RentalAgent;
import rw.ikodesha.repository.RentalAgentRepository;

@Service
public class RentalAgentService {

    @Autowired 
    private RentalAgentRepository rentalAgentRepo;

    public String saveRentalAgent(RentalAgent rentalAgent) {

        rentalAgentRepo.save(rentalAgent);
        return "Rental agent saved successfully";
    }

    public List<RentalAgent> getAllRentalAgents() {
        return rentalAgentRepo.findAll();
    }

    public Optional<RentalAgent> getRentalAgentById(UUID id) {
        return rentalAgentRepo.findById(id);
    }

    public String updateRentalAgent(UUID id, RentalAgent rentalAgent) {
        Optional<RentalAgent> found = rentalAgentRepo.findById(id);
        if (found.isEmpty()) {
            return "The rental agent with that id does not exist";
        }
        RentalAgent existing = found.get();
        existing.setFirstName(rentalAgent.getFirstName());
        existing.setLastName(rentalAgent.getPhone());
        existing.setZone(rentalAgent.getZone());
        rentalAgentRepo.save(existing);
        return "Rental agent update successfully";
    }

    public String deleteRentalAgent(UUID id) {
        if (!rentalAgentRepo.existsById(id)) {
            return "The rental agent with that id does not exist";
        }
        rentalAgentRepo.deleteById(id);
        return "Rental agent deleted successfully";
    }
    public List<RentalAgent> getRentalAgentsByPropertyType(String name) {
        return rentalAgentRepo.searchByPropertyType(name);
    }
    
}
