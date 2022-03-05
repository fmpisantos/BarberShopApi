package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Shop.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicesRepository  extends JpaRepository<Services, Long> {

}