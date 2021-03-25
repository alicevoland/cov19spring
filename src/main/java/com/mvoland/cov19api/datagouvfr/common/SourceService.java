package com.mvoland.cov19api.datagouvfr.common;

import java.time.Duration;
import java.time.LocalDate;

public class SourceService<ValueType> {

    private final ValueParser<ValueType> valueParser;
    private final ValueSelector<ValueType> valueSelector;
    private final ValueProcessor<ValueType> valueProcessor;
    private final DataSourceConfig dataSourceConfig;
    private SourceCheckedUpdater<ValueType> sourceCheckedUpdater;

    public SourceService(
            DataSourceConfig dataSourceConfig,
            ValueParser<ValueType> valueParser,
            ValueProcessor<ValueType> valueProcessor,
            ValueSelector<ValueType> valueSelector
    ) {
        this.dataSourceConfig = dataSourceConfig;
        this.valueParser = valueParser;
        this.valueSelector = valueSelector;
        this.valueProcessor = valueProcessor;


        sourceCheckedUpdater = new SourceCheckedUpdater<>(
                new SourceUpdater<>(
                        new SourceFetcher<>(dataSourceConfig, valueParser),
                        valueProcessor,
                        valueSelector
                ),
                Duration.ofHours(12)
        );
    }

    public String getSourceName() {
        return dataSourceConfig.getSourceName();
    }

    public boolean requestFullUpdate() {
        return sourceCheckedUpdater.requestFullUpdate();
    }

    public boolean requestUpdateSinceEntryDate(LocalDate date) {
        return sourceCheckedUpdater.requestUpdateSinceEntryDate(date);
    }

}
