package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Barber.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBarberRepository  extends JpaRepository<Barber, Long> {

}
