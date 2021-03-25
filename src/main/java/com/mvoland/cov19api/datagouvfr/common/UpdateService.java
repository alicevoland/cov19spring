package com.mvoland.cov19api.datagouvfr.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public boolean requestUpdate(String dataSourceName) {
        LOGGER.info("Request update '{}'", dataSourceName);
        if (sourceServiceMap.containsKey(dataSourceName.toLowerCase())) {
            return sourceServiceMap.get(dataSourceName.toLowerCase()).requestUpdate();
        } else if (dataSourceName.equalsIgnoreCase("all")) {
            return sourceServiceMap.values().stream()
                    .map(SourceService::requestUpdate)
                    .collect(Collectors.toList())
                    .stream().anyMatch(Boolean::booleanValue);
        }
        LOGGER.warn("Unknown data source {}", dataSourceName);
        return false;
    }

    public List<String> getSourceNames() {
        return sourceServiceMap.values().stream()
                .map(SourceService::getSourceName)
                .collect(Collectors.toList());
    }

}
