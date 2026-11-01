package rw.ikodesha.repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rw.ikodesha.domain.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, UUID> {
    @Query ("SELECT z FROM Zone z WHERE NOT EXISTS (SELECT a FROM RentalAgent a WHERE a.zone = z) ORDER BY z.zoneNumber")
    List<Zone> findFreeZones();
    
    Optional<Zone> findByZoneNumber(int zoneNumber);
}
