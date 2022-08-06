
package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT C.* FROM client as C INNER JOIN history as H ON H.client_id = C.id WHERE H.shop_id = :shopId", nativeQuery = true)
    List<Client> findAllClientsByShopId(Long shopId);

    @Query(value = "SELECT * FROM client WHERE name = :name AND phone = :phone ", nativeQuery = true)
    List<Client> findByNameAndPhone(String name, String phone);
}