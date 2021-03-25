package com.mvoland.cov19api.datagouvfr.common;

import java.time.Duration;

public class SourceService<ValueType> {

    private SourceInfo<ValueType> sourceInfo;
    private SourceCheckedUpdater<ValueType> sourceCheckedUpdater;

    public SourceService(
            SourceInfo<ValueType> sourceInfo
    ) {
        this.sourceInfo = sourceInfo;

        sourceCheckedUpdater = new SourceCheckedUpdater<>(
                new SourceUpdater<>(
                        new SourceFetcher<>(
                                sourceInfo.getSourceName(), sourceInfo.getSourceUrl(), sourceInfo.getValueParser()
                        ),
                        sourceInfo.getValueProcessor()
                ),
                Duration.ofHours(12)
        );
    }

    public String getSourceName() {
        return sourceInfo.getSourceName();
    }

    public boolean requestUpdate() {
        return sourceCheckedUpdater.requestUpdate();
    }

}
