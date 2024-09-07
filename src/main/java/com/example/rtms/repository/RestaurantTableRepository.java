package com.example.rtms.repository;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

import com.example.rtms.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    @Query(value = "SELECT CASE WHEN status = 'OCCUPIED' THEN true ELSE false END " +
            "FROM restaurant_tables " +
            "WHERE id = ?1", nativeQuery = true)
    boolean isOccupied(Long tableId);

    @Query(value = "SELECT * FROM restaurant_tables WHERE status = 'AVAILABLE' and active = true", nativeQuery = true)
    List<RestaurantTable> listAvailableTables();
}
