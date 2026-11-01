package rw.ikodesha.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikodesha.domain.Zone;
import rw.ikodesha.repository.ZoneRepository;

@Service
public class ZoneService {
   
    
    @Autowired 
    private ZoneRepository zoneRepo;

    public String saveZone(Zone zone) {
        zoneRepo.save(zone);
        return "Zonesaved successfully";
    }
    public List<Zone> getAllZones() {
        return zoneRepo.findAll();
    }

    public Optional<Zone> getZoneById(UUID id){
        return zoneRepo.findById(id);
    }

    public String updateZone(UUID id, Zone zone) {
        Optional<Zone> found = zoneRepo.findById(id);
        if (found.isEmpty()) {
            return "The zone with that id does not exist";
        }
        Zone existing = found.get();
        existing.setZoneNumber(zone.getZoneNumber());
        existing.setName(zone.getName());
        zoneRepo.save(existing);
        return "Zone updated successfully";
    }

    public String deleteZone(UUID id) {
        if (!zoneRepo.existsById(id)) {
            return "The zone with that id does not exist";
        }
        zoneRepo.deleteById(id);
        return "Zone deleted succesfully";
    }
}
