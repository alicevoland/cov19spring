package com.mvoland.cov19api.datagouvfr.common;

public interface ValueProcessor<ValueType> {

    void process(ValueType value);
}
