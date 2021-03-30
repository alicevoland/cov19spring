package com.mvoland.cov19api.datasource.hospdata;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.DepartmentalHospitalisation;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datasource.common.DataGouvFrCsvDataSource;
import com.mvoland.cov19api.datasource.common.ParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class DonneesHospitalieresCovid19Source extends DataGouvFrCsvDataSource<DonneesHospitalieresCovid19> {

    private final LocalityService localityService;
    private final HospitalisationService hospitalisationService;

    @Autowired
    public DonneesHospitalieresCovid19Source(
            LocalityService localityService,
            HospitalisationService hospitalisationService
    ) {
        super(
                "DonneesHospitalieresCovid19",
                "https://www.data.gouv.fr/fr/datasets/r/63352e38-d353-4b54-bfd1-f1b3ee1cabd7",
                StandardCharsets.ISO_8859_1,
                ';',
                '\"',
                1
        );
        this.localityService = localityService;
        this.hospitalisationService = hospitalisationService;
    }

    @Override
    protected DonneesHospitalieresCovid19 parse(String[] rowValues) throws Exception {
        DonneesHospitalieresCovid19 data = new DonneesHospitalieresCovid19();
        data.setDep(rowValues[0]);
        data.setSexe(ParsingUtils.parseSexOrThrow(rowValues[1]));
        data.setJour(ParsingUtils.parseDateOrThrow(rowValues[2]));
        data.setHosp(ParsingUtils.parseIntegerOrDefault((rowValues[3]), 0));
        data.setRea(ParsingUtils.parseIntegerOrDefault((rowValues[4]), 0));
        data.setRad(ParsingUtils.parseIntegerOrDefault((rowValues[5]), 0));
        data.setDc(ParsingUtils.parseIntegerOrDefault((rowValues[6]), 0));
        return data;
    }

    @Override
    protected boolean select(DonneesHospitalieresCovid19 value, LocalDate date) {
        return value.getJour().isAfter(date);
    }

    @Override
    protected void process(DonneesHospitalieresCovid19 value) {
        localityService
                .findDepartmentByCode(value.getDep())
                .ifPresent(department ->
                        hospitalisationService.safeUpdateDepartmentalHospitalisation(
                                new DepartmentalHospitalisation(
                                        department, value.getJour(), value.getSexe(),
                                        value.getHosp(), value.getRea(),
                                        value.getRad(), value.getDc()
                                )
                        ));
    }

}
