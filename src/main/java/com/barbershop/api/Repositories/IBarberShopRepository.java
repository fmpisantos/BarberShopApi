package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Shop.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IBarberShopRepository extends JpaRepository<BarberShop, Long> {
    @Query(value = "SELECT SB.*, B.Name as barberName, BS.public_name as shopName " +
            "FROM shop_barber SB " +
            "INNER JOIN barber B ON B.id = SB.barber_id " +
            "INNER JOIN barber_shop BS ON BS.id = shop_id " +
            "WHERE SB.shop_id = :shopId AND SB.barber_id = :barberId", nativeQuery = true)
    List<Map<String, Object>> findBarberShopRelation(Long shopId, Long barberId);

    @Query(value = "SELECT SB.schedule, SB.photo, B.name as barberName, BS.public_name as shopName " +
            "FROM shop_barber SB " +
            "INNER JOIN barber B ON B.id = SB.barber_id " +
            "INNER JOIN barber_shop BS ON BS.id = SB.shop_id " +
            "WHERE SB.shop_id = :shopId AND SB.active = true AND BS.active = true and B.active = true", nativeQuery = true)
    List<Map<String, Object>> listSchedules(Long shopId);
}