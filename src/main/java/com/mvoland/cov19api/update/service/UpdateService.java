package com.mvoland.cov19api.update.service;

import com.mvoland.cov19api.update.data.UpdateRequest;
import com.mvoland.cov19api.update.data.UpdateRepository;
import com.mvoland.cov19api.datagouvfr.hospdata.service.DataGouvFrHospDataUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateService {

    private final Logger LOGGER = LoggerFactory.getLogger(UpdateService.class);

    private final UpdateRepository updateRepository;
    private final DataGouvFrHospDataUpdateService dataGouvFrHospDataUpdateService;

    public Duration getDelayFor(String dataSource) {
        return Duration.ofHours(12);
    }

    @Autowired
    public UpdateService(
            UpdateRepository updateRepository,
            DataGouvFrHospDataUpdateService dataGouvFrHospDataUpdateService
    ) {
        this.updateRepository = updateRepository;
        this.dataGouvFrHospDataUpdateService = dataGouvFrHospDataUpdateService;
    }

    public Optional<UpdateRequest> getLastUpdateRequestFor(String dataSource) {
        return updateRepository
                .findByDataSource(dataSource).stream()
                .min(Comparator.comparingLong(u -> u.getDurationSinceRequest().toMillis()));
    }


    @Transactional
    public UpdateRequest newUpdateRequest(String dataSource) {
        if ("hospdata-datagouvfr".equalsIgnoreCase(dataSource)) {
            if (getLastUpdateRequestFor("hospdata-datagouvfr")
                    .map(UpdateRequest::getDurationSinceRequest)
                    // if duration d is bigger than minimal delay to wait
                    .map(d -> d.compareTo(getDelayFor("hospdata-datagouvfr")) > 0)
                    .orElse(true)) {
                return updateRepository.save(new UpdateRequest(
                        "hospdata-datagouvfr", LocalDateTime.now(), false
                ));
            } else {
                throw new RuntimeException("Too soon");
            }
        }
        throw new RuntimeException("Uknown datasource " + dataSource);
    }

    @Transactional
    public void completed(UpdateRequest request) {
        request.setCompleted(true);
        updateRepository.save(request);
    }

    public Optional<UpdateRequest> safeNewHospDataDataGouvFrUpdateRequest() {
        try {
            UpdateRequest request = newUpdateRequest("hospdata-datagouvfr");
            Runnable runnable = () -> {
                dataGouvFrHospDataUpdateService.update(false);
                completed(request);
            };
            new Thread(runnable).start();
            return Optional.of(request);
        } catch (Exception e) {
            LOGGER.info("Cannot create update request because {}", e.getMessage());
            return Optional.empty();
        }
    }

    public List<UpdateRequest> getAllUpdateRequests() {
        return updateRepository.findAll();
    }


}
