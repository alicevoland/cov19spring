package com.mvoland.cov19api.datagouvfr.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

public class SourceCheckedUpdater<ValueType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceCheckedUpdater.class);

    private final SourceUpdater<ValueType> sourceUpdater;
    private final Duration delay;

    private Thread updateThread;
    private LocalDateTime lastUpdateStart;
    private LocalDateTime lastUpdateEnd;

    public SourceCheckedUpdater(
            SourceUpdater<ValueType> sourceUpdater,
            Duration delay
    ) {
        this.sourceUpdater = sourceUpdater;
        this.delay = delay;

        updateThread = null;
        lastUpdateStart = null;
        lastUpdateEnd = null;
    }

    private synchronized void updateNow() {
        lastUpdateStart = LocalDateTime.now();
        lastUpdateEnd = null;
        updateThread = new Thread(() -> {
            try {
                sourceUpdater.update();
            } catch (Exception e) {
                LOGGER.info("Update error", e);
            } finally {
                this.lastUpdateEnd = LocalDateTime.now();
                this.updateThread = null;
            }
        });
        updateThread.start();
    }

    public synchronized boolean requestUpdate() {
        LOGGER.debug("Update request");
        if (updateThread != null) {
            LOGGER.debug("Update request REJECTED (update currently running)");
            return false;
        }
        if (lastUpdateStart != null && LocalDateTime.now().isBefore(lastUpdateStart.plus(delay))) {
            LOGGER.debug("Update request REJECTED (need to wait)");
            return false;
        }
        LOGGER.debug("Update request ACCEPTED");
        updateNow();
        return true;
    }
}
