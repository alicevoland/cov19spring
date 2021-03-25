package com.mvoland.cov19api.datagouvfr.common;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SourceFetcher<ValueType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceFetcher.class);

    private final String sourceName;
    private final String sourceUrl;
    private final ValueParser<ValueType> valueParser;

    public SourceFetcher(
            String sourceName,
            String sourceUrl,
            ValueParser<ValueType> valueParser
    ) {
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.valueParser = valueParser;
    }

    public List<ValueType> fetchAll() {
        List<ValueType> data = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(new URL(sourceUrl).openStream(), StandardCharsets.ISO_8859_1))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').withQuoteChar('\"').build())
                .withSkipLines(1)
                .build()) {

            csvReader.forEach(row -> {
                try {
                    ValueType value = valueParser.parse(row);
                    data.add(value);
                } catch (Exception e) {
                    LOGGER.warn("{}: Could not parse values {} because {}", sourceName, row, e.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            LOGGER.warn("{}: Could not fetch {} because {} ", sourceName, sourceUrl, e.getLocalizedMessage());
        }

        return data;
    }

    public String getSourceName() {
        return sourceName;
    }
}
