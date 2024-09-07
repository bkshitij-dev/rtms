package com.example.rtms.model;

/*
 * @author Kshitij
 * @created 06-Sep-2024
 */

import com.example.rtms.enums.ReservationRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservation_requests")
public class ReservationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_request_seq")
    @SequenceGenerator(name = "reservation_request_seq", sequenceName = "reservation_request_seq", allocationSize = 1)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_contact", nullable = false)
    private String customerContact;

    @Column(name = "pax", nullable = false)
    private Integer pax;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReservationRequestStatus status = ReservationRequestStatus.PENDING;

    @Column(name = "reservation_request_time", nullable = false)
    private LocalDateTime reservationRequestTime;
}
