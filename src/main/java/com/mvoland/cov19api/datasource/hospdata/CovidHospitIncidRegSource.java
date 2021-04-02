package com.mvoland.cov19api.datasource.hospdata;

import com.mvoland.cov19api.covidstat.hospitalisation.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.covidstat.hospitalisation.service.HospitalisationService;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datasource.common.DataGouvFrCsvDataSource;
import com.mvoland.cov19api.common.ParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class CovidHospitIncidRegSource extends DataGouvFrCsvDataSource<CovidHospitIncidReg> {

    private final LocalityService localityService;
    private final HospitalisationService hospitalisationService;

    @Autowired
    public CovidHospitIncidRegSource(
            LocalityService localityService,
            HospitalisationService hospitalisationService
    ) {
        super(
                "CovidHospitIncidReg",
                "https://www.data.gouv.fr/fr/datasets/r/a1466f7f-4ece-4158-a373-f5d4db167eb0",
                StandardCharsets.ISO_8859_1,
                ';',
                '\"',
                1
        );
        this.localityService = localityService;
        this.hospitalisationService = hospitalisationService;
    }

    @Override
    protected CovidHospitIncidReg parse(String[] rowValues) throws Exception {
        CovidHospitIncidReg data = new CovidHospitIncidReg();
        data.setJour(ParsingUtils.parseDateOrThrow(rowValues[0]));
        data.setNomReg(rowValues[1]);
        data.setNumReg(rowValues[2]);
        data.setIncid_rea(ParsingUtils.parseIntegerOrDefault(rowValues[3], 0));
        return data;
    }

    @Override
    protected boolean select(CovidHospitIncidReg value, LocalDate date) {
        return value.getJour().isAfter(date);
    }

    @Override
    protected void process(CovidHospitIncidReg value) {
        localityService
                .findRegionByCode(value.getNumReg())
                .ifPresent(region -> hospitalisationService.safeUpdateRegionalIntensiveCareAdmission(new RegionalIntensiveCareAdmission(
                        region, value.getJour(), value.getIncid_rea())));
    }

}
