package com.mvoland.cov19api.data.repository;

import com.mvoland.cov19api.data.entity.RegionIntensiveCareAdmission;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RegionIntensiveCareAdmissionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionIntensiveCareAdmissionRepository.class);

    private String csvUrl = "https://www.data.gouv.fr/fr/datasets/r/a1466f7f-4ece-4158-a373-f5d4db167eb0";
    private String charsetName = "ISO-8859-1";

    private List<RegionIntensiveCareAdmission> regionIntensiveCareAdmissionList = null;

    synchronized private void checkOrPopulateRegionIntensiveCareAdmissionList() {
        if (this.regionIntensiveCareAdmissionList != null) {
            LOGGER.debug("Using cached version of regionIntensiveCareAdmissionList");
            return;
        }

        LOGGER.info("Will fetch {}", csvUrl);

        this.regionIntensiveCareAdmissionList = new ArrayList<>();

        try (CSVReader csvReader =
                     new CSVReaderBuilder(new InputStreamReader(new URL(csvUrl).openStream(), Charset.forName(charsetName)))
                             .withSkipLines(1)
                             .withCSVParser(new CSVParserBuilder().withSeparator(';').withQuoteChar('\"').build())
                             .build()) {

            csvReader.forEach(values -> {
                try {
                    RegionIntensiveCareAdmission regionIntensiveCareAdmission = new RegionIntensiveCareAdmission();

                    regionIntensiveCareAdmission.setJour(values[0]);
                    regionIntensiveCareAdmission.setNomReg(values[1]);
                    regionIntensiveCareAdmission.setNumReg(Integer.parseInt(values[2]));
                    regionIntensiveCareAdmission.setIncid_rea(Integer.parseInt((values[3])));

                    regionIntensiveCareAdmissionList.add(regionIntensiveCareAdmission);

                } catch (Exception e) {
                    LOGGER.warn("Could not parse values {} ", values);
                }
            });

        } catch (IOException e) {
            LOGGER.warn("Error {}", e.getLocalizedMessage());
        }

        LOGGER.info("Got {} records!", regionIntensiveCareAdmissionList.size());


    }

    public List<RegionIntensiveCareAdmission> findAll() {
        checkOrPopulateRegionIntensiveCareAdmissionList();
        return regionIntensiveCareAdmissionList;
    }

}
