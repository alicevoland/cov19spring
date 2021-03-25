package com.mvoland.cov19api.datagouvfr.common;

import java.time.LocalDate;

public interface ValueSelector<ValueType> {
    default boolean selectByEntryDateAfter(ValueType value, LocalDate date) {
        return true;
    }
}
