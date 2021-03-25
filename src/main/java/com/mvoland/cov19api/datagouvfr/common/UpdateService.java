package com.mvoland.cov19api.datagouvfr.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateService.class);

    private final Map<String, SourceService<?>> sourceServiceMap;

    public UpdateService(SourceService<?>... sourceServices) {
        sourceServiceMap = new HashMap<>();
        for (SourceService<?> sourceService : sourceServices) {
            sourceServiceMap.put(sourceService.getSourceName().toLowerCase(), sourceService);
        }
    }

    public boolean requestUpdate(String dataSourceName, LocalDate sinceDate) {
        LOGGER.info("Request update '{}' since {}", dataSourceName, sinceDate == null ? "ALL" : sinceDate.toString());

        if (sourceServiceMap.containsKey(dataSourceName.toLowerCase())) {
            return sourceServiceMap.get(dataSourceName.toLowerCase()).requestUpdateSinceEntryDate(sinceDate);

        } else if (dataSourceName.equalsIgnoreCase("all")) {
            return sourceServiceMap.values().stream()
                    .map(service -> service.requestUpdateSinceEntryDate(sinceDate))
                    .collect(Collectors.toList())
                    .stream().anyMatch(Boolean::booleanValue);
        }
        LOGGER.warn("Unknown data source {}", dataSourceName);
        return false;
    }

    public boolean requestUpdateByDays(String dataSourceName, Integer days) {
        LocalDate date = days == null
                ? null
                : LocalDate.now().minusDays(days);
        return requestUpdate(dataSourceName, date);
    }

    public List<String> getSourceNames() {
        return sourceServiceMap.values().stream()
                .map(SourceService::getSourceName)
                .collect(Collectors.toList());
    }

}
