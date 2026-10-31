package rw.ikodesha.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name ="rental_agent")
public class RentalAgent{

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @ManyToMany
    @JoinTable(
        name = "rental_agent_property_type",
        joinColumns = @JoinColumn(name ="renal_agent_id"),
        inverseJoinColumns = @JoinColumn(name = "property_type_id")
    )
    private List<PropertyType> propertyTypes = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) {this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
   
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }

    public List<PropertyType> getPropertyTypes() { return propertyTypes; }
    public void setPropertyTypes(List<PropertyType> propertyTypes) { this.propertyTypes = propertyTypes; }
}