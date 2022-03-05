
package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Relations.ShopBarber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopBarberRepository  extends JpaRepository<ShopBarber, Long> {

}