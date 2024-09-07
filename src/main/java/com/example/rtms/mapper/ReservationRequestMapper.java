package com.example.rtms.mapper;

/*
 * @author Kshitij
 * @created 07-Sep-2024
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationRequestMapper {

    void updateStatusFromReservation(@Param("reservationId") Long reservationId, @Param("status") String status);

    void updateStatus(@Param("id") Long id, @Param("status") String status);
}
