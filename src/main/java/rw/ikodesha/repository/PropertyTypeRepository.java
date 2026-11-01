package rw.ikodesha.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import rw.ikodesha.domain.PropertyType;


@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, UUID> {
    @Query ("SELECT p FROM PropertyType p WHERE p.rentalAgents IS EMPTY")
    List<PropertyType> findUnusedPropertyTypes();
}
