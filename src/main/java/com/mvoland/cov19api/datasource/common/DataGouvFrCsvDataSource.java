package com.mvoland.cov19api.datasource.common;

import com.mvoland.cov19api.common.util.PercentCounter;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DataGouvFrCsvDataSource<ValueType> implements DataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataGouvFrCsvDataSource.class);

    private final String sourceName;
    private final String csvUrl;
    private final Charset csvCharset;
    private final Character csvSeparator;
    private final Character csvQuote;
    private final int csvSkip;

    public DataGouvFrCsvDataSource(
            String sourceName,
            String csvUrl,
            Charset csvCharset,
            Character csvSeparator,
            Character csvQuote,
            int csvSkip
    ) {
        this.sourceName = sourceName;
        this.csvUrl = csvUrl;
        this.csvCharset = csvCharset;
        this.csvSeparator = csvSeparator;
        this.csvQuote = csvQuote;
        this.csvSkip = csvSkip;
    }

    protected abstract ValueType parse(String[] row) throws Exception;

    protected abstract boolean select(ValueType value, LocalDate date);

    protected abstract void process(ValueType value);

    @Override
    public String getSourceName() {
        return sourceName;
    }

    protected void update(LocalDate date, boolean fullUpdate) {

        LOGGER.info("{}: Will update {}", sourceName, fullUpdate ? "FULL" : String.format("since %s", date.toString()));

        List<ValueType> allValues = new ArrayList<>();

        try (CSVReader csvReader =
                     new CSVReaderBuilder(new InputStreamReader(new URL(csvUrl).openStream(), csvCharset))
                             .withCSVParser(
                                     new CSVParserBuilder()
                                             .withSeparator(csvSeparator)
                                             .withQuoteChar(csvQuote)
                                             .build()
                             )
                             .withSkipLines(csvSkip)
                             .build()) {

            csvReader.forEach(row -> {
                try {
                    ValueType value = parse(row);
                    allValues.add(value);
                } catch (Exception e) {
                    LOGGER.warn("{}: Could not parse values {}", sourceName, row, e);
                }
            });

        } catch (Exception e) {
            LOGGER.warn("{}: Could not fetch {}", sourceName, csvUrl, e);
        }

        List<ValueType> selectedValues = allValues.stream()
                .filter(value -> fullUpdate || select(value, date))
                .collect(Collectors.toList());

        LOGGER.info("{}: selected {} values from {} in total", sourceName, selectedValues.size(), allValues.size());

        PercentCounter counter = new PercentCounter(selectedValues.size(), 10,
                (tick, size, percent) -> LOGGER.info("{} {}% ({} / {})", sourceName, percent, tick, size)
        );

        selectedValues.parallelStream().forEach(value -> {
                    try {
                        process(value);
                    } catch (Exception e) {
                        LOGGER.warn("{}: Could not process {}", sourceName, value, e);
                    }
                    counter.tick();
                }
        );

        LOGGER.info("{}: ** DONE ** updated {}", sourceName, fullUpdate ? "FULL" : String.format("since %s", date.toString()));

    }

    @Override
    public void fullUpdate() {
        update(null, true);
    }

    @Override
    public void updateSince(LocalDate date) {
        update(date, false);
    }
}
