package com.mvoland.cov19api.common;

import com.mvoland.cov19api.common.type.AgeGroup;
import com.mvoland.cov19api.common.type.Sex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class ParsingUtils {

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

    public static LocalDate parseDateOrThrow(String value, Supplier<RuntimeException> exceptionSupplier) {
        try {
            return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            throw exceptionSupplier.get();
        }
    }

    public static AgeGroup parseAgeGroupOrThrow(String value) {
        AgeGroup ageGroup = AgeGroup.fromValue(parseIntegerOrThrow(value));
        if (ageGroup.equals(AgeGroup.UNKNOWN)) {
            throw new IllegalArgumentException("Unknown age group: '" + value + "'");
        }
        return ageGroup;
    }

    public static Sex parseSexOrThrow(String value) {
        Sex sex = Sex.fromValue(parseIntegerOrThrow(value));
        if (sex.equals(Sex.UNKNOWN)) {
            throw new IllegalArgumentException("Unknown sex: '" + value + "'");
        }
        return sex;
    }

}