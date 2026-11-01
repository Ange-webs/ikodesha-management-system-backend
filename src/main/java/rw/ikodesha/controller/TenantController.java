package rw.ikodesha.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ikodesha.domain.Tenant;
import rw.ikodesha.service.TenantService;

@RestController 
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired 
    private TenantService tenantService;

    @PostMapping("/save")
    public ResponseEntity<String> saveTenant(@RequestBody Tenant tenant) {
    String msg = tenantService.saveTenant(tenant);
    return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return new ResponseEntity<>(tenantService.getAllTenants(), HttpStatus.OK);
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Tenant>> getTenantsByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(tenantService.getTenantsByLastName(lastName), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTenantById(@PathVariable UUID id) {
        Optional<Tenant> tenant = tenantService.getTenantById(id);
        if (tenant.isPresent()) {
            return new ResponseEntity<>(tenant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The tenant with that id does not exist", HttpStatus.NOT_FOUND);
        }


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTenant(@PathVariable UUID id, @RequestBody Tenant tenant) {
       String msg = tenantService.updateTenant(id, tenant);
       if (msg.equals("Tenant updated successfully")) {
        return new ResponseEntity<>(msg,HttpStatus.OK);
       } else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
       }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTenant(@PathVariable UUID id) {

    String msg = tenantService.deleteTenant(id);
    if (msg.equals("Tenant deleted successfully")) {
    return new ResponseEntity<>(msg, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
      }
  }
}