package rw.ikodesha.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.ikodesha.domain.RentalAgent;
import rw.ikodesha.domain.Zone;
@Repository
public interface RentalAgentRepository extends JpaRepository<RentalAgent, UUID> {
    @Query("SELECT DISTINCT a FROM RentalAgent a JOIN a.propertyTypes p WHERE LOWER(p.name) = LOWER(:name)")
    List<RentalAgent> searchByPropertyType(@Param("name") String name);

    boolean existsByZoneAndIdNot(Zone zone, UUID id);
}
