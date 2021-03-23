package com.mvoland.cov19api.datagouvfr.hospdata.misc;

import java.time.Duration;
import java.time.LocalDateTime;

public class UpdateProcess {
    private final Duration waitAfterFinishedUpdate;
    private final Duration waitAfterUnifishedUpdate;
    private final LocalDateTime startTime;

    public UpdateProcess(
            Duration waitAfterFinishedUpdate,
            Duration waitAfterUnifishedUpdate
    ) {
        this.startTime = LocalDateTime.now();
        this.waitAfterFinishedUpdate = waitAfterFinishedUpdate;
        this.waitAfterUnifishedUpdate = waitAfterUnifishedUpdate;
    }

    public Duration getWaitAfterFinishedUpdate() {
        return waitAfterFinishedUpdate;
    }

    public Duration getWaitAfterUnifishedUpdate() {
        return waitAfterUnifishedUpdate;
    }
}
