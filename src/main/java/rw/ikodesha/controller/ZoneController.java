package rw.ikodesha.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import rw.ikodesha.domain.Zone;
import rw.ikodesha.service.ZoneService;

@RestController 
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired 
    private ZoneService zoneService;

    @PostMapping("/save")
    public ResponseEntity<String> saveZone(@RequestBody Zone zone) {
        String msg = zoneService.saveZone(zone);
    return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Zone>> getAllZones() {
        return new ResponseEntity<>(zoneService.getAllZones(), HttpStatus.OK);

    }

    @GetMapping ("/free")
    public ResponseEntity<List<Zone>> getFreeZones() {
        return new  ResponseEntity<>(zoneService.getFreeZones(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getZoneById(@PathVariable UUID id) {
        Optional<Zone> zone = zoneService.getZoneById(id);
        if (zone.isPresent()){
            return new ResponseEntity<>(zone.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>("The zone with that id does not exist", HttpStatus.NOT_FOUND);
    }
}
@PutMapping("/update/{id}")
public ResponseEntity<String> updateZone(@PathVariable UUID id, @RequestBody Zone zone) {
    String msg = zoneService.updateZone(id, zone);
    if (msg.equals("Zone updated successfully")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteZone(@PathVariable UUID id) {
    String msg = zoneService.deleteZone(id);
    if (msg.equals("Zone deleted successfully")) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
  }
}


    

