package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Client.Client;
import com.barbershop.api.Models.Relations.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface IHistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "SELECT H.* FROM history AS H WHERE H.shop_id = :shopId AND H.date_time::::varchar LIKE :datetime ", nativeQuery = true)
    List<History> historyByShopAndDate(Long shopId, String datetime);

}