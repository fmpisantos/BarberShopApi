package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Relations.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryRepository  extends JpaRepository<History, Long> {

}