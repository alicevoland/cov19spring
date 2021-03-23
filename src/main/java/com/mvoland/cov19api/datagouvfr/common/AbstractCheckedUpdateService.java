package com.mvoland.cov19api.datagouvfr.common;

public abstract class AbstractCheckedUpdateService<ValueType> {

    private final AbstractUpdateService<ValueType> updateService;

    public AbstractCheckedUpdateService(
            AbstractUpdateService<ValueType> updateService
    ) {
        this.updateService = updateService;
    }

    public boolean requestUpdate() {
        return false;
    }
}
