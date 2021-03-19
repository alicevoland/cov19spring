package com.mvoland.cov19api.hospdata.datagouvfr.hospdata.common;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DataGouvCsvBackend<DataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataGouvCsvBackend.class);

    private Function<String[], DataType> entityParser;
    private String permanentUrl;
    private Duration expiration;
    private Character separator;
    private Character quoteChar;
    private String charsetName;

    private LocalTime lastFetched;
    private List<DataType> cache;

    public DataGouvCsvBackend(Function<String[], DataType> entityParser,
                              String permanentUrl, Duration expiration,
                              Character separator, Character quoteChar, String charsetName) {
        this.entityParser = entityParser;
        this.permanentUrl = permanentUrl;
        this.separator = separator;
        this.quoteChar = quoteChar;
        this.charsetName = charsetName;
        this.expiration = expiration;
        this.lastFetched = null;
        this.cache = null;
    }

    public DataGouvCsvBackend(Function<String[], DataType> entityParser, String permanentUrl) {
        this(entityParser, permanentUrl, Duration.ofHours(12),
                ';', '\"', "ISO-8859-1");
    }

    private synchronized boolean isCacheExpired() {
        if (this.lastFetched == null || this.cache == null) {
            return true;
        }
        LocalTime expiryTime = this.lastFetched.plus(this.expiration);
        return expiryTime.isBefore(LocalTime.now());
    }

    private synchronized List<DataType> fetch() {
        List<DataType> data = new ArrayList<>();
        try (CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(new URL(this.permanentUrl).openStream(), Charset.forName(this.charsetName)))
                .withCSVParser(new CSVParserBuilder().withSeparator(this.separator).withQuoteChar(this.quoteChar).build())
                .withSkipLines(1)
                .build()) {

            csvReader.forEach(row -> {
                try {
                    DataType value = this.entityParser.apply(row);
                    data.add(value);
                } catch (Exception e) {
                    LOGGER.warn("Could not parse values {} because {}", row, e.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            LOGGER.warn("Could not fetch {} beacause {} ", this.permanentUrl, e.getLocalizedMessage());
        }
        return data;
    }

    public synchronized List<DataType> getOrFetch() {
        if (isCacheExpired()) {
            LOGGER.info("Need to fetch: {}", permanentUrl);
            this.cache = fetch();
            LOGGER.info("Fetching done");
            this.lastFetched = LocalTime.now();
        }
        return this.cache;
    }
}
