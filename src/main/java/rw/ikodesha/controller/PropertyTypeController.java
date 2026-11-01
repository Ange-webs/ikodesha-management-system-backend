package rw.ikodesha.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ikodesha.domain.PropertyType;
import rw.ikodesha.service.PropertyTypeService;

@RestController
@RequestMapping("/api/property-types")
public class PropertyTypeController {

    @Autowired
    private PropertyTypeService propertyTypeService;

    @PostMapping("/save")
    public ResponseEntity<String> savePropertyType(@RequestBody PropertyType propertyType) {
        String msg = propertyTypeService.savePropertyType(propertyType);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyType>> getAllPropertyTypes() {
        return new ResponseEntity<>(propertyTypeService.getAllPropertyTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyTypeById(@PathVariable UUID id) {
        Optional<PropertyType> propertyType = propertyTypeService.getPropertyTypeById(id);
        if (propertyType.isPresent()) {
            return new ResponseEntity<>(propertyType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The property type with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePropertyType(@PathVariable UUID id, @RequestBody PropertyType propertyType) {
        String msg = propertyTypeService.updatePropertyType(id, propertyType);
        if (msg.equals("Property type updated successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePropertyType(@PathVariable UUID id) {
        String msg = propertyTypeService.deletePropertyType(id);
        if (msg.equals("Property type deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }
}