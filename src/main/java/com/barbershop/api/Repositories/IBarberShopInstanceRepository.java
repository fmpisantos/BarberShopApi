package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Shop.BarberShopInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarberShopInstanceRepository extends JpaRepository<BarberShopInstance, Long> {

}