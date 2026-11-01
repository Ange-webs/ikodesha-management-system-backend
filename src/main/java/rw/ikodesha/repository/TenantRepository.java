package rw.ikodesha.repository;

import java.util.List;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ikodesha.domain.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {
 List<Tenant> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);  
}
