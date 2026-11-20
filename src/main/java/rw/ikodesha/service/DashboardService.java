package rw.ikodesha.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ikodesha.domain.ApplicationStatus;
import rw.ikodesha.repository.RentPaymentRepository;
import rw.ikodesha.repository.RentalAgentRepository;
import rw.ikodesha.repository.RentalApplicationRepository;
import rw.ikodesha.repository.TenantRepository;
import rw.ikodesha.repository.ZoneRepository;


@Service

public class DashboardService {

    @Autowired 
    private TenantRepository tenantRepo;

    @Autowired 
    private RentalApplicationRepository rentalApplicationRepo;

    @Autowired 
    private RentPaymentRepository rentPaymentRepo;

    @Autowired 
    private RentalAgentRepository rentalAgentRepo;

    @Autowired 
    private ZoneRepository zoneRepo;


    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("tenants", tenantRepo.count());
        summary.put("rentalAgents", rentalAgentRepo.count());
        summary.put("freezones",(long) zoneRepo.findFreeZones().size());
        summary.put("moveInsToday", rentalApplicationRepo.countByMoveInDate(LocalDate.now()));
        summary.put("submitted", rentalApplicationRepo.countByStatus(ApplicationStatus.SUBMITTED));
        summary.put("rentPayments", rentPaymentRepo.count());
        return summary;
    }
    
}
