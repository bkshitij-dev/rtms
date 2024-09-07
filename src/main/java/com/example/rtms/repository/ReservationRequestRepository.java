package com.example.rtms.repository;

/*
 * @author Kshitij
 * @created 06-Sep-2024
 */

import com.example.rtms.model.ReservationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
}
