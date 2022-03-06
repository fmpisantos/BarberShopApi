package com.barbershop.api.Repositories;

        import com.barbershop.api.Models.Relations.BarberServices;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface IBarberServiceRepository  extends JpaRepository<BarberServices, Long> {

}