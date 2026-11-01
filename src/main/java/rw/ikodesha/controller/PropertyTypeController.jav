package rw.ikodesha.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ttp.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.ikodesha.domain.PropertyType;
import rw.ikodesha.service.PropertTypeServervice;

@RestController
@RequestMapping("/api/property-types")
    public class PropertyTypeController {

        @Autowired
        private PropertyTypeService propertyTypeService;

        @PostMapping("/save")
        public ResponseEntity<String> savaPropeertyType(@RequestBody PropertyType propertyType) {
            String msg = propertyTypeService.savePropertyType(propertyType);
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        
        } 

        @GetMapping("/all")
        public ResponseEntity<List<PropertyType>> getAllPropertyTypes() {
            return new ResponseEntity<>(propertyTypesService.getAllPropertyTypes(), HttpStatus.OK)

        }
        
        @GetMapping("/{id}")
        public ResponseEntity<?> getPropertyTypeById(@PathVariable UUID id) {
            Optional<PropertyType> propertyType = propertyTypeService.getAllPropertyTypeById(id);
            if (propertyType.isPresnt()) {
                return new ResponseEntity<>(propertyType.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(" The property type with that id does not exist", HttpStatus.NotFOUND);

            }
        }
    }
    @DeleteMapping("/delete/{id}")
    public RepositoryEntity<String> deletePropertType(@PathVlariable UUID id) {
        string msg = propertyTypeService.deletePropertyType(id);
        if (msg.equals("Property type deleted successfully")) {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }
}

