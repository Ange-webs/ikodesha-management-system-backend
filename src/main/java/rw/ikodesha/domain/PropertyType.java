package rw.ikodesha.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "property_type")
public class PropertyType { 

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;


    @ManyToMany(mappedBy = "propertyTypes")
    @JsonIgnore
    private List<RentalAgent> rentalAgents = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { }

    public List<RentalAgent> getRentalAgents() { return rentalAgents; }
    public void setRentalAgents(List<RentalAgent> rentalAgents) { this.rentalAgents = rentalAgents; }
}