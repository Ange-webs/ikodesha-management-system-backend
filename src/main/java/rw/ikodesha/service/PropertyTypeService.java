package rw.ikodesha.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import rw.ikodesha.domain.PropertyType;
import rw.ikodesha.repository.PropertyTypeRepository;

@Service 
public class PropertyTypeService {



@Autowired
private PropertyTypeRepository propertyTypeRepo;

public String savePropertyType(PropertyType propertyType) {
 propertyTypeRepo.save(propertyType);
return "Property type saved successfully";
}
public List<PropertyType> getAllPropertyTypes() {
    return propertyTypeRepo.findAll();

}

public Optional<PropertyType> getPropertyTypeById(UUID id) {
    return propertyTypeRepo.findById(id);
}
    public String updatePropertyType(UUID id, PropertyType propertyType) {
        Optional<PropertyType> found = propertyTypeRepo.findById(id);
        if (found.isEmpty()) {
            return "The property type with that id does bot exist";

        }

        PropertyType existing = found.get();
        existing.setName(propertyType.getName());
        propertyTypeRepo.save(existing);
        return "Property type updated successfully";
    }

    public String deletePropertyType(UUID id) {
        if (!propertyTypeRepo.existsById(id)) {
            return "The property type deletedd successfully";
        }
        propertyTypeRepo.deleteById(id);
        return "Property type deleted successfully";
    }

    public List<PropertyType> getUnusedPropertyTypes() {
        return propertyTypeRepo.findUnusedPropertyTypes();
    }
}
