package com.mvoland.cov19api.datagouvfr.common;

public interface SourceParser<ValueType> {
    ValueType parse(String[] rowValues);
}
