package rw.ikodesha.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rw.ikodesha.domain.PropertyType;
import rw.ikodesha.domain.RentalAgent;
import rw.ikodesha.domain.Zone;
import rw.ikodesha.repository.PropertyTypeRepository;
import rw.ikodesha.repository.RentalAgentRepository;
import rw.ikodesha.repository.ZoneRepository;

@Service
public class RentalAgentService {

    @Autowired 
    private RentalAgentRepository rentalAgentRepo;

    @Autowired 
    private PropertyTypeRepository propertyTypeRepo;

    @Autowired 
    private ZoneRepository zoneRepo;

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

    @Transactional 
    public String addPropertyType(UUID rentalAgentId, UUID propertyTypeId) {
    Optional<RentalAgent> foundAgent = rentalAgentRepo.findById(rentalAgentId);
     if (foundAgent.isEmpty()) {
        return "The rental agent with that id does not exist";
     } 
     Optional<PropertyType> foundType = propertyTypeRepo.findById(propertyTypeId);
     if (foundType.isEmpty()){
        return "That property does not exist";
     }  
     RentalAgent rentalAgent = foundAgent.get();
     PropertyType propertyType = foundType.get();
     if (rentalAgent.getPropertyTypes().contains(propertyType)) {
        return "The rental agent already has that property type";
     }  
     rentalAgent.getPropertyTypes().add(propertyType);
     rentalAgentRepo.save(rentalAgent);
     return "Property type added to rental agent";
    }

    public String changeZone(UUID rentalAgentId, int zoneNumber) {
        Optional<RentalAgent> foundAgent = rentalAgentRepo.findById(rentalAgentId);
        if (foundAgent.isEmpty()) {
            return "The rental agent with that id doesnot exist";
        }
        Optional<Zone> foundZone =zoneRepo.findByZoneNumber(zoneNumber);
        if (foundZone.isEmpty()) {
            return "The zone with that zoneNumber does not exist";
        }
        Zone zone = foundZone.get();
        if (rentalAgentRepo.existsByZoneAndIdNot(zone, rentalAgentId)) {
            return "Thezone iz taken by another rentalagent";
        }
        RentalAgent rentalAgent = foundAgent.get();
        rentalAgent.setZone(zone);
        rentalAgentRepo.save(rentalAgent);
        return "Zone changed successfully";
    }
    
}
