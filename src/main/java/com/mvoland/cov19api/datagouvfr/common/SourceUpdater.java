package com.mvoland.cov19api.datagouvfr.common;

import com.mvoland.cov19api.common.util.PercentCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public  class SourceUpdater<ValueType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceUpdater.class);

    private final SourceFetcher<ValueType> sourceFetcher;
    private final ValueProcessor<ValueType> valueProcessor;

    public SourceUpdater(
            SourceFetcher<ValueType> sourceFetcher,
            ValueProcessor<ValueType> valueProcessor
    ) {
        this.sourceFetcher = sourceFetcher;
        this.valueProcessor = valueProcessor;
    }

    public void update() {
        LOGGER.info("Will update {}", sourceFetcher.getSourceName());
        List<ValueType> values = sourceFetcher.fetchAll();
        LOGGER.info("Got {} values", values.size());

        PercentCounter counter = new PercentCounter(
                values.size(), 10, (tick, size, percent) ->
                LOGGER.info("{} - {}%: {} / {}", sourceFetcher.getSourceName(), percent, tick, size));
        values.parallelStream()
                .forEach(value -> {
                    try {
                        valueProcessor.process(value);
                    } catch (Exception e) {
                        LOGGER.warn("{}: Could not process value {} because {}", sourceFetcher.getSourceName(), value, e);
                    }
                    counter.tick();
                });
    }
}
