package com.barbershop.api.Repositories;

import com.barbershop.api.Models.LoginIds.LoginIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILoginIdsRepository extends JpaRepository<LoginIds, Long> {
    @Query(value = "SELECT * FROM login_ids WHERE type = :type AND platform_id = :id", nativeQuery = true)
    List<LoginIds> findByTypeAndId(String type, String id);
}
