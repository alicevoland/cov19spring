package com.mvoland.cov19api.datagouvfr.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DataSourceConfig {

    private final String sourceName;
    private final String sourceUrl;
    private final Character csvDelim;
    private final Character csvQuote;
    private final Charset csvCharset;
    private final Integer csvSkipLine;

    public DataSourceConfig(
            String sourceName,
            String sourceUrl,
            Character csvDelim,
            Character csvQuote,
            Charset csvCharset,
            Integer csvSkipLine
    ) {
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.csvDelim = csvDelim;
        this.csvQuote = csvQuote;
        this.csvCharset = csvCharset;
        this.csvSkipLine = csvSkipLine;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Character getCsvDelim() {
        return csvDelim;
    }

    public Character getCsvQuote() {
        return csvQuote;
    }

    public Charset getCsvCharset() {
        return csvCharset;
    }

    public Integer getCsvSkipLine() {
        return csvSkipLine;
    }

    public static DataSourceConfig forHospData(
            String sourceName,
            String sourceUrl
    ) {
        return new DataSourceConfig(
                sourceName,
                sourceUrl,
                ';',
                '\"',
                StandardCharsets.ISO_8859_1,
                1
        );
    }
}
