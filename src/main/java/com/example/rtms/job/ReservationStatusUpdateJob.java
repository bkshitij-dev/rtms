package com.example.rtms.job;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */

import com.example.rtms.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationStatusUpdateJob {

    private final ReservationService reservationService;

    private static final Logger logger = LoggerFactory.getLogger(ReservationStatusUpdateJob.class);

    @Scheduled(cron = "0 */1 * ? * *")
    public void completeReservation() throws Exception {
        logger.info("Completing reservation >>>>>>");
        reservationService.completeReservation();
        logger.info("<<<<<< Successfully completed reservation");
    }
}
