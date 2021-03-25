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

    private final ValueParser<ValueType> valueParser;
    private final DataSourceConfig dataSourceConfig;

    public SourceFetcher(
            DataSourceConfig dataSourceConfig,
            ValueParser<ValueType> valueParser
    ) {
        this.dataSourceConfig = dataSourceConfig;
        this.valueParser = valueParser;
    }


    public List<ValueType> fetchAll() {
        List<ValueType> data = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(
                new InputStreamReader(
                        new URL(dataSourceConfig.getSourceUrl()).openStream(), dataSourceConfig.getCsvCharset()
                ))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(dataSourceConfig.getCsvDelim())
                        .withQuoteChar(dataSourceConfig.getCsvQuote())
                        .build())
                .withSkipLines(dataSourceConfig.getCsvSkipLine())
                .build()) {

            csvReader.forEach(row -> {
                try {
                    ValueType value = valueParser.parse(row);
                    data.add(value);
                } catch (Exception e) {
                    LOGGER.warn("{}: Could not parse values {} because {}", dataSourceConfig.getSourceName(), row, e.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            LOGGER.warn("{}: Could not fetch {} because {} ", dataSourceConfig.getSourceName(), dataSourceConfig.getSourceUrl(), e.getLocalizedMessage());
        }

        return data;
    }

    public String getSourceName() {
        return dataSourceConfig.getSourceName();
    }
}
