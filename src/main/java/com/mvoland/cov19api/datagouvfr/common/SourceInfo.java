package com.mvoland.cov19api.datagouvfr.common;

import lombok.Value;

import java.time.Duration;

public class SourceInfo<ValueType> {

    private String sourceName;
    private String sourceUrl;
    private Duration updateDelay;
    private ValueParser<ValueType> valueParser;
    private ValueProcessor<ValueType> valueProcessor;

    private SourceFetcher<ValueType> sourceFetcher;
    private SourceUpdater<ValueType> sourceUpdater;
    private SourceCheckedUpdater<ValueType> sourceCheckedUpdater;

    public SourceInfo(
            String sourceName,
            String sourceUrl,
            Duration updateDelay,
            ValueParser<ValueType> valueParser,
            ValueProcessor<ValueType> valueProcessor
    ) {
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.updateDelay = updateDelay;
        this.valueParser = valueParser;
        this.valueProcessor = valueProcessor;

        sourceFetcher = new SourceFetcher<>(sourceName, sourceUrl, valueParser);
        sourceUpdater = new SourceUpdater<>(sourceFetcher, valueProcessor);
        sourceCheckedUpdater = new SourceCheckedUpdater<>(sourceUpdater, updateDelay);
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Duration getUpdateDelay() {
        return updateDelay;
    }

    public ValueParser<ValueType> getValueParser() {
        return valueParser;
    }

    public ValueProcessor<ValueType> getValueProcessor() {
        return valueProcessor;
    }


    public SourceFetcher<ValueType> getSourceFetcher() {
        return sourceFetcher;
    }

    public SourceUpdater<ValueType> getSourceUpdater() {
        return sourceUpdater;
    }

    public SourceCheckedUpdater<ValueType> getSourceCheckedUpdater() {
        return sourceCheckedUpdater;
    }
}
