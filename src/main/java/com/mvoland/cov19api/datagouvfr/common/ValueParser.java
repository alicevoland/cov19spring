package com.mvoland.cov19api.datagouvfr.common;

public interface ValueParser<ValueType> {
    ValueType parse(String[] rowValues);
}
