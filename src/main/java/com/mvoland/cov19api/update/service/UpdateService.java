package com.mvoland.cov19api.data.update;

import com.mvoland.cov19api.data.update.entity.UpdateRequest;
import com.mvoland.cov19api.data.update.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Comparator;
import java.util.Optional;

@Service
public class UpdateService {
    private final UpdateRepository updateRepository;

    public Duration getDelayFor(String dataSource) {
        return Duration.ofHours(12);
    }

    @Autowired
    public UpdateService(
            UpdateRepository updateRepository
    ) {
        this.updateRepository = updateRepository;
    }

    public Optional<UpdateRequest> getLastUpdateRequestFor(String dataSource) {
        return updateRepository
                .findByDataSource(dataSource).stream()
                .min(Comparator.comparingLong(u -> u.getDurationSinceRequest().toMillis()));
    }


}
