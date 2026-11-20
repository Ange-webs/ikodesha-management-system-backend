package rw.ikodesha.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity 
@Table(name = "rent_payment")
public class RentPayment {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
     
    private String channel;

    private String reference;

    private int amountRwf;

    private LocalDate paidDate;


    @ManyToOne 
    @JoinColumn (name = "rental_application_id")
    private RentalApplication rentalApplication;

    public UUID getId() { return id; }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getAmountRwf() {
        return amountRwf;
    }

    public void setAmountRwf(int amountRwf) {
        this.amountRwf = amountRwf;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public RentalApplication getRentalApplication() {
        return rentalApplication;
    }

    public void setRentalApplication(RentalApplication rentalApplication) {
        this.rentalApplication = rentalApplication;
    }
    

}
