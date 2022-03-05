package com.barbershop.api.Repositories;

import com.barbershop.api.Models.Client.Client;
import com.barbershop.api.Models.Relations.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IHistoryRepository extends JpaRepository<History, Long> {
}