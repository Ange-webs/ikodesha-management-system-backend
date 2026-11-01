package rw.ikodesha.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import rw.ikodesha.domain.PropertyType;


@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, UUID> {
    
}
