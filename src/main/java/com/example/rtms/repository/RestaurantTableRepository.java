package com.example.rtms.repository;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

import com.example.rtms.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}
