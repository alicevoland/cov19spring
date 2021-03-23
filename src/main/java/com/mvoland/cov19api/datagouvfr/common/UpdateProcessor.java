package com.mvoland.cov19api.datagouvfr.common;

public interface UpdateProcessor<ValueType> {

    void process(ValueType value);
}
