package com.mvoland.cov19api.datagouvfr.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SourceCheckedUpdater<ValueType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceCheckedUpdater.class);

    private final SourceUpdater<ValueType> sourceUpdater;
    private final Duration fullUpdateDelay;

    private Thread updateThread;
    private LocalDateTime lastUpdateStart;
    private LocalDateTime lastUpdateEnd;

    public SourceCheckedUpdater(
            SourceUpdater<ValueType> sourceUpdater,
            Duration fullUpdateDelay
    ) {
        this.sourceUpdater = sourceUpdater;
        this.fullUpdateDelay = fullUpdateDelay;

        updateThread = null;
        lastUpdateStart = null;
        lastUpdateEnd = null;
    }

    private synchronized void updateNow(LocalDate sinceDate) {
        lastUpdateStart = LocalDateTime.now();
        lastUpdateEnd = null;
        updateThread = new Thread(() -> {
            try {
                sourceUpdater.updateSinceEntryDate(sinceDate);
            } catch (Exception e) {
                LOGGER.info("Update error", e);
            } finally {
                this.lastUpdateEnd = LocalDateTime.now();
                this.updateThread = null;
            }
        });
        updateThread.start();
    }

    public synchronized boolean requestFullUpdate() {
        LOGGER.debug("Update request");
        if (updateThread != null) {
            LOGGER.debug("Update request REJECTED (update currently running)");
            return false;
        }
        if (lastUpdateStart != null && LocalDateTime.now().isBefore(lastUpdateStart.plus(fullUpdateDelay))) {
            LOGGER.debug("Update request REJECTED (need to wait)");
            return false;
        }
        LOGGER.debug("Update request ACCEPTED");
        updateNow(null);
        return true;
    }

    public synchronized boolean requestUpdateSinceEntryDate(LocalDate date) {
        LOGGER.debug("Update request");
        if (updateThread != null) {
            LOGGER.debug("Update request REJECTED (update currently running)");
            return false;
        }
        LOGGER.debug("Partial update request ACCEPTED");
        updateNow(date);
        return true;
    }


}
