package com.example.rtms;

import com.example.rtms.dto.request.RegisterRequestDto;
import com.example.rtms.dto.request.ReservationRequestDto;
import com.example.rtms.dto.request.RestaurantTableRequestDto;
import com.example.rtms.dto.request.RoleRequestDto;
import com.example.rtms.enums.RoleType;
import com.example.rtms.service.ReservationService;
import com.example.rtms.service.RestaurantTableService;
import com.example.rtms.service.RoleService;
import com.example.rtms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final RestaurantTableService restaurantTableService;
    private final ReservationService reservationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleService.count() <= 0) {
            createRoles();
        }
        if (userService.count() <= 0) {
            createUsers();
        }
//        if (restaurantTableService.count() <= 0) {
//            createRestaurantTables();
//        }
//        if (reservationService.count() <= 0) {
//            createReservations();
//        }
    }

    private void createRoles() {
        RoleRequestDto adminRole = RoleRequestDto.builder().name(RoleType.ROLE_ADMIN.name()).build();
        roleService.create(adminRole);

        RoleRequestDto staffRole = RoleRequestDto.builder().name(RoleType.ROLE_STAFF.name()).build();
        roleService.create(staffRole);

        RoleRequestDto customerRole = RoleRequestDto.builder().name(RoleType.ROLE_CUSTOMER.name()).build();
        roleService.create(customerRole);
    }

    private void createUsers() {
        RegisterRequestDto admin = RegisterRequestDto.builder()
                .name("Admin")
                .username("admin")
                .email("admin@app.com")
                .password("Admin@123")
                .build();
        userService.create(admin, List.of(RoleType.ROLE_ADMIN, RoleType.ROLE_STAFF));

        RegisterRequestDto staff = RegisterRequestDto.builder()
                .name("Staff")
                .username("staff")
                .email("staff@app.com")
                .password("Staff@123")
                .build();
        userService.create(staff, List.of(RoleType.ROLE_STAFF));

        RegisterRequestDto customer = RegisterRequestDto.builder()
                .name("Customer")
                .username("customer")
                .email("customer@app.com")
                .password("Customer@123")
                .build();
        userService.create(customer, List.of(RoleType.ROLE_CUSTOMER));
    }

    private void createRestaurantTables() {
        RestaurantTableRequestDto table1 = RestaurantTableRequestDto.builder()
                .name("Table 1")
                .seatingCapacity(5)
                .build();
        restaurantTableService.create(table1);

        RestaurantTableRequestDto table2 = RestaurantTableRequestDto.builder()
                .name("Table 2")
                .seatingCapacity(3)
                .build();
        restaurantTableService.create(table2);

        RestaurantTableRequestDto table3 = RestaurantTableRequestDto.builder()
                .name("Table 3")
                .seatingCapacity(10)
                .build();
        restaurantTableService.create(table3);

        RestaurantTableRequestDto table4 = RestaurantTableRequestDto.builder()
                .name("Table 4")
                .seatingCapacity(5)
                .build();
        restaurantTableService.create(table4);

        RestaurantTableRequestDto table5 = RestaurantTableRequestDto.builder()
                .name("Table 5")
                .seatingCapacity(6)
                .build();
        restaurantTableService.create(table5);

        RestaurantTableRequestDto table6 = RestaurantTableRequestDto.builder()
                .name("Table 6")
                .seatingCapacity(4)
                .build();
        restaurantTableService.create(table6);

        RestaurantTableRequestDto table7 = RestaurantTableRequestDto.builder()
                .name("Table 7")
                .seatingCapacity(4)
                .build();
        restaurantTableService.create(table7);

        RestaurantTableRequestDto table8 = RestaurantTableRequestDto.builder()
                .name("Table 8")
                .seatingCapacity(5)
                .build();
        restaurantTableService.create(table8);

        RestaurantTableRequestDto table9 = RestaurantTableRequestDto.builder()
                .name("Table 9")
                .seatingCapacity(4)
                .build();
        restaurantTableService.create(table9);

        RestaurantTableRequestDto table10 = RestaurantTableRequestDto.builder()
                .name("Table 10")
                .seatingCapacity(2)
                .build();
        restaurantTableService.create(table10);
    }

    private void createReservations() {
        ReservationRequestDto reservation1 = ReservationRequestDto.builder()
                .customerName("Customer 1")
                .customerEmail("customer1@email.com")
                .customerContact("1234567890")
                .pax(2)
                .build();
        reservationService.create(reservation1);

        ReservationRequestDto reservation2 = ReservationRequestDto.builder()
                .customerName("Customer 2")
                .customerEmail("customer2@email.com")
                .customerContact("1234567890")
                .pax(5)
                .build();
        reservationService.create(reservation2);

        ReservationRequestDto reservation3 = ReservationRequestDto.builder()
                .customerName("Customer 3")
                .customerEmail("customer3@email.com")
                .customerContact("1234567890")
                .pax(3)
                .build();
        reservationService.create(reservation3);

        ReservationRequestDto reservation4 = ReservationRequestDto.builder()
                .customerName("Customer 4")
                .customerEmail("customer4@email.com")
                .customerContact("1234567890")
                .pax(5)
                .build();
        reservationService.create(reservation4);

        ReservationRequestDto reservation5 = ReservationRequestDto.builder()
                .customerName("Customer 5")
                .customerEmail("customer5@email.com")
                .customerContact("1234567890")
                .pax(6)
                .build();
        reservationService.create(reservation5);

        ReservationRequestDto reservation6 = ReservationRequestDto.builder()
                .customerName("Customer 6")
                .customerEmail("customer6@email.com")
                .customerContact("1234567890")
                .pax(2)
                .build();
        reservationService.create(reservation6);

        ReservationRequestDto reservation7 = ReservationRequestDto.builder()
                .customerName("Customer 7")
                .customerEmail("customer7@email.com")
                .customerContact("1234567890")
                .pax(3)
                .build();
        reservationService.create(reservation7);

        ReservationRequestDto reservation8 = ReservationRequestDto.builder()
                .customerName("Customer 8")
                .customerEmail("customer8@email.com")
                .customerContact("1234567890")
                .pax(4)
                .build();
        reservationService.create(reservation8);

        ReservationRequestDto reservation9 = ReservationRequestDto.builder()
                .customerName("Customer 9")
                .customerEmail("customer9@email.com")
                .customerContact("1234567890")
                .pax(8)
                .build();
        reservationService.create(reservation9);
    }
}
