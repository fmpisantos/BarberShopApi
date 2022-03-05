
package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {

}