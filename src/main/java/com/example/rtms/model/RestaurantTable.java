package com.example.rtms.model;

import com.example.rtms.enums.TableStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "restaurant_tables",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_restaurant_table_name", columnNames = {"name"})
        })
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_table_seq")
    @SequenceGenerator(name = "restaurant_table_seq", sequenceName = "restaurant_table_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "seating_capacity", nullable = false)
    private Integer seatingCapacity;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TableStatus status;

    @Column(name = "active")
    @Builder.Default
    private boolean active = true;
}
