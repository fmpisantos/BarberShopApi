package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Relations.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface IHistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "SELECT H.*, S.name as serviceName, S.duration as duration, B.name as barberName, C.name as clientName " +
            "FROM history AS H " +
            "INNER JOIN services S ON H.service_id = S.id " +
            "INNER JOIN barber B ON B.id = H.barber_id " +
            "INNER JOIN client C ON C.id = H.client_id " +
            "WHERE H.shop_id = :shopId AND H.date_time::::varchar LIKE :datetime  "+
            "ORDER BY H.date_time asc", nativeQuery = true)
    List<Map<String, Object>> historyByShopAndDate(Long shopId, String datetime);

    @Query(value = "SELECT H.*, S.name as serviceName, S.duration as duration, B.name as barberName, C.name as clientName " +
                    "FROM history AS H " +
                    "INNER JOIN services S ON H.service_id = S.id " +
                    "INNER JOIN barber B ON B.id = H.barber_id " +
                    "INNER JOIN client C ON C.id = H.client_id " +
                    "WHERE H.barber_id = :barberId AND H.date_time::::varchar LIKE :datetime "+
                    "ORDER BY H.date_time asc", nativeQuery = true)
    List<Map<String, Object>> historyByBarberAndDate(Long barberId, String datetime);

    @Query(value = "SELECT H.*, S.name as serviceName, S.duration as duration, B.name as barberName, C.name as clientName " +
            "FROM history AS H " +
            "INNER JOIN services S ON H.service_id = S.id " +
            "INNER JOIN barber B ON B.id = H.barber_id " +
            "INNER JOIN client C ON C.id = H.client_id " +
            "WHERE H.shop_id = :shopId AND H.barber_id = :barberId AND H.date_time::::varchar LIKE :datetime  "+
            "ORDER BY H.date_time asc", nativeQuery = true)
    List<Map<String, Object>> historyByShopBarberAndDate(Long shopId, Long barberId, String datetime);

    @Query(value = "SELECT H.*, S.name as serviceName, S.duration as duration, B.name as barberName, C.name as clientName " +
            "FROM history AS H " +
            "INNER JOIN services S ON H.service_id = S.id " +
            "INNER JOIN barber B ON B.id = H.barber_id " +
            "INNER JOIN client C ON C.id = H.client_id " +
            "WHERE H.client_id = :clientId AND H.date_time::::varchar LIKE :datetime  "+
            "ORDER BY H.date_time asc", nativeQuery = true)
    List<Map<String, Object>> historyByClientAndDate(Long clientId, String datetime);

    @Query(value = "SELECT Id " +
            "FROM history " +
            "WHERE shop_id = :shopId AND barber_id = :barberId AND date_time::::varchar LIKE :datetime ", nativeQuery = true)
    List<Map<String, Object>> checkAvailability(Long shopId, String datetime, Long barberId);
}