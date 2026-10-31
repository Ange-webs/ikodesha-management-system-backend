package rw.ikodesha.domain;

import java.time.LocalDate;
import java.util.UUID;


import jakarta.persistence.*;

@Entity
@Table(name = "rental_application")
public class RentalApplication { 

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private LocalDate moveInDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private String houseCode;

    private int monthlyRent;


    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "rental_agent_id")
    private RentalAgent rentalAgent;


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDate getMoveInDate() { return moveInDate; }
    public void setMoveInDate(LocalDate moveInDate) { this.moveInDate = moveInDate; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public String getHouseCode() { return houseCode; }
    public void setHouseCode( String houseCode) { this.houseCode = houseCode; }

    public int getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(int monthlyRent) { this.monthlyRent = monthlyRent; }

    public Tenant getTenant() { return tenant; }
    public void setTenant(Tenant tenant) { this.tenant = tenant; }

    public RentalAgent getRentalAgent() { return rentalAgent; }
    public void setRentalAgent(RentalAgent rentalAgent) { this.rentalAgent = rentalAgent; } 

}