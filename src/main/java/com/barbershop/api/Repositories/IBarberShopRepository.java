package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Shop.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarberShopRepository extends JpaRepository<BarberShop, Long> {

}