## Restaurant Table Management System

### Tech Stack Used
- Java 17
- Spring Boot 3.3.3
- PostgreSQL 16

### Project setup
- Clone the repo
- Create PostgreSQL database rtmsdb with username postgres and password postgres
- Run the application
- View swagger ui at http://localhost:8080/swagger-ui.html

### Features
- User can add/update/view/remove restaurant tables
- User can add/update/view/remove reservations
- Each reservation is valid for 2 hours (on creation status is set to <strong>CONFIRMED</strong> and upon completion it is set to <strong>COMPLETED</strong>)
- Reservation start time and end time are set
- A job runs every minute to check if reservation end time has been surpassed, upon which the reservation status is set to <strong>COMPLETED</strong> and table status is set to <strong>AVAILABLE</strong>.
- When new reservation is added, check is to assign the best available table, such that the seating capacity is greater or equal to the requested pax, and the table with the least number of seating capacity meeting this criteria is selected.
- If no table is available, error message is thrown showing the estimated wait time.
- Data initialized is added, so restaurant tables and reservations data are set on first app run.