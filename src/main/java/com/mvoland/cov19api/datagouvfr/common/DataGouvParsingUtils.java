package com.mvoland.cov19api.datagouvfr.common;

import com.mvoland.cov19api.common.type.AgeGroup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGouvParsingUtils {

    public static Integer parseIntegerOrDefault(String value, Integer defaultInteger) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultInteger;
        }
    }


    public static Integer parseIntegerOrThrow(String value) {
        return Integer.parseInt(value);
    }

    public static LocalDate parseDateOrThrow(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static AgeGroup parseAgeGroupOrThrow(String value) {
        AgeGroup ageGroup = AgeGroup.fromValue(parseIntegerOrThrow(value));
        if (ageGroup.equals(AgeGroup.UNKNOWN)) {
            throw new IllegalArgumentException("Unknown age group: '" + value + "'");
        }
        return ageGroup;
    }
}
