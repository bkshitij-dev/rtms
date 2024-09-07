package com.example.rtms.repository;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

import com.example.rtms.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservations WHERE CURRENT_TIMESTAMP > reservation_end_time::timestamp " +
            "AND status != 'COMPLETED'", nativeQuery = true)
    List<Reservation> findAllTimeExceeded();

}
