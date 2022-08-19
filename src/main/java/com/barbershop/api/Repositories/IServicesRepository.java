package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Shop.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IServicesRepository  extends JpaRepository<Services, Long> {
    @Query(value = "SELECT B.* " +
            "FROM Barber B " +
            "INNER JOIN barber_services SB ON SB.barber_id = B.id " +
            "WHERE SB.service_id = :serviceId", nativeQuery = true)
    List<Map<String, Object>> listBarbers(Long serviceId);
}