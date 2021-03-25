package com.mvoland.cov19api.datagouvfr.common;

import com.mvoland.cov19api.common.util.PercentCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SourceUpdater<ValueType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceUpdater.class);

    private final SourceFetcher<ValueType> sourceFetcher;
    private final ValueProcessor<ValueType> valueProcessor;
    private final ValueSelector<ValueType> valueSelector;

    public SourceUpdater(
            SourceFetcher<ValueType> sourceFetcher,
            ValueProcessor<ValueType> valueProcessor,
            ValueSelector<ValueType> valueSelector
    ) {
        this.sourceFetcher = sourceFetcher;
        this.valueProcessor = valueProcessor;
        this.valueSelector = valueSelector;
    }

    public void fullUpdate() {
        updateSinceEntryDate(null);
    }

    public void updateSinceEntryDate(LocalDate date) {
        LOGGER.info("Will update {}", sourceFetcher.getSourceName());

        List<ValueType> allValues = sourceFetcher.fetchAll();
        LOGGER.info("Got {} values total", allValues.size());

        List<ValueType> values = allValues.stream()
                .filter(value -> valueSelector.selectByEntryDateAfter(value, date))
                .collect(Collectors.toList());
        LOGGER.info("Got {} values selected", values.size());

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
