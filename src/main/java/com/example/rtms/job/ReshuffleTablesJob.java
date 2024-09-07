package com.example.rtms.job;

/*
 * @author Kshitij
 * @created 07-Sep-2024
 */

import com.example.rtms.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReshuffleTablesJob {

    private final ReservationService reservationService;

    private static final Logger logger = LoggerFactory.getLogger(ReshuffleTablesJob.class);

    @Scheduled(cron = "0 */1 * ? * *")
    public void reshuffleTables() throws Exception {
        logger.info("Reshuffling tables >>>>>>");
        reservationService.reshuffleTables();
        logger.info("<<<<<< Successfully reshuffled tables");
    }
}
