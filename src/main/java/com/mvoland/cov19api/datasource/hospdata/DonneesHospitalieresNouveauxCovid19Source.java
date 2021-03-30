package com.mvoland.cov19api.datasource.hospdata;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.DepartmentalNewHospitalisation;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datasource.common.DataGouvFrCsvDataSource;
import com.mvoland.cov19api.datasource.common.ParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class DonneesHospitalieresNouveauxCovid19Source extends DataGouvFrCsvDataSource<DonneesHospitalieresNouveauxCovid19> {

    private final LocalityService localityService;
    private final HospitalisationService hospitalisationService;

    @Autowired
    public DonneesHospitalieresNouveauxCovid19Source(
            LocalityService localityService,
            HospitalisationService hospitalisationService
    ) {
        super(
                "DonneesHospitalieresNouveauxCovid19",
                "https://www.data.gouv.fr/fr/datasets/r/6fadff46-9efd-4c53-942a-54aca783c30c",
                StandardCharsets.ISO_8859_1,
                ';',
                '\"',
                1
        );
        this.localityService = localityService;
        this.hospitalisationService = hospitalisationService;
    }

    @Override
    protected DonneesHospitalieresNouveauxCovid19 parse(String[] rowValues) throws Exception {
        DonneesHospitalieresNouveauxCovid19 data = new DonneesHospitalieresNouveauxCovid19();
        data.setDep(rowValues[0]);
        data.setJour(ParsingUtils.parseDateOrThrow(rowValues[1]));
        data.setIncid_hosp(ParsingUtils.parseIntegerOrDefault(rowValues[2], 0));
        data.setIncid_rea(ParsingUtils.parseIntegerOrDefault((rowValues[3]), 0));
        data.setIncid_dc(ParsingUtils.parseIntegerOrDefault(rowValues[4], 0));
        data.setIncid_rad(ParsingUtils.parseIntegerOrDefault(rowValues[5], 0));
        return data;
    }

    @Override
    protected boolean select(DonneesHospitalieresNouveauxCovid19 value, LocalDate date) {
        return value.getJour().isAfter(date);
    }

    @Override
    protected void process(DonneesHospitalieresNouveauxCovid19 value) {
        localityService
                .findDepartmentByCode(value.getDep())
                .ifPresent(department ->
                        hospitalisationService.safeUpdateDepartmentalNewHospitalisation(
                                new DepartmentalNewHospitalisation(
                                        department, value.getJour(),
                                        value.getIncid_hosp(), value.getIncid_rea(),
                                        value.getIncid_rad(), value.getIncid_dc()
                                )
                        ));
    }

}
