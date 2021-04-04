package com.mvoland.cov19api.datasource.hospdata;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datasource.common.DataGouvFrCsvDataSource;
import com.mvoland.cov19api.common.util.ParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class DonneesHospitalieresClasseAgeCovid19Source extends DataGouvFrCsvDataSource<DonneesHospitalieresClasseAgeCovid19> {

    private final LocalityService localityService;
    private final HospitalisationService hospitalisationService;

    @Autowired
    public DonneesHospitalieresClasseAgeCovid19Source(
            LocalityService localityService,
            HospitalisationService hospitalisationService
    ) {
        super(
                "DonneesHospitalieresClasseAgeCovid19",
                "https://www.data.gouv.fr/fr/datasets/r/08c18e08-6780-452d-9b8c-ae244ad529b3",
                StandardCharsets.ISO_8859_1,
                ';',
                '\"',
                1
        );
        this.localityService = localityService;
        this.hospitalisationService = hospitalisationService;
    }

    @Override
    protected DonneesHospitalieresClasseAgeCovid19 parse(String[] rowValues) throws Exception {
        DonneesHospitalieresClasseAgeCovid19 data = new DonneesHospitalieresClasseAgeCovid19();
        data.setReg(rowValues[0]);
        data.setCl_age90(ParsingUtils.parseAgeGroupOrThrow(rowValues[1]));
        data.setJour(ParsingUtils.parseDateOrThrow(rowValues[2]));
        data.setHosp(ParsingUtils.parseIntegerOrDefault(rowValues[3], 0));
        data.setRea(ParsingUtils.parseIntegerOrDefault(rowValues[4], 0));
        data.setRad(ParsingUtils.parseIntegerOrDefault(rowValues[5], 0));
        data.setDc(ParsingUtils.parseIntegerOrDefault(rowValues[6], 0));
        return data;
    }

    @Override
    protected boolean select(DonneesHospitalieresClasseAgeCovid19 value, LocalDate date) {
        return value.getJour().isAfter(date);
    }

    @Override
    protected void process(DonneesHospitalieresClasseAgeCovid19 value) {
        localityService
                .findRegionByCode(value.getReg())
                .ifPresent(region -> hospitalisationService.safeUdateRegionalHospitalisation(
                        new RegionalHospitalisation(
                                region,
                                value.getJour(),
                                value.getCl_age90(),
                                value.getHosp(), value.getRea(),
                                value.getRad(), value.getDc()
                        ))
                );
    }

}
