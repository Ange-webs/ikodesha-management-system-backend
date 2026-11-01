package rw.ikodesha.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import rw.ikodesha.domain.Tenant;
import rw.ikodesha.repository.TenantRepository;

@Service
public class TenantService {
    
    @Autowired 
    private TenantRepository tenantRepo;

    public String saveTenant(Tenant tenant) {
        tenantRepo.save(tenant);
        return "Tenant saved successfully";
    }
    public List<Tenant> getAllTenants() {
        return tenantRepo.findAll();
    }
    public Optional<Tenant> getTenantById(UUID id) {
        return tenantRepo.findById(id);
    }

    public String updateTenant(UUID id, Tenant tenant) {
        Optional<Tenant> found = tenantRepo.findById(id);
        if (found.isEmpty()) {
            return "The tenant with that id does not exist";
        }
        Tenant existing = found.get();
        existing.setFirstName(tenant.getFirstName());
        existing.setLastName(tenant.getLastName());
        existing.setNationalId(tenant.getNationalId());
        existing.setphone(tenant.getPhone());
        tenantRepo.save(existing);
        return "Tenant updated successfully";

    }
    public String deleteTenant(UUID id) {
        if (!tenantRepo.existsById(id)) {
            return "The tenant with that id does not exist";

        }
        tenantRepo.deleteById(id);
        return "Tenant deleted successfully";
    }
}
