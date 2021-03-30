package com.mvoland.cov19api.datasource.depdefr;

import com.mvoland.cov19api.covidstat.locality.data.Department;
import com.mvoland.cov19api.covidstat.locality.data.Region;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datasource.common.DataGouvFrCsvDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class DepartementDeFranceSource extends DataGouvFrCsvDataSource<DepartementDeFrance> {


    private final LocalityService localityService;

    @Autowired
    public DepartementDeFranceSource(
            LocalityService localityService
    ) {
        super(
                "DepartementDeFrance",
                "https://www.data.gouv.fr/fr/datasets/r/70cef74f-70b1-495a-8500-c089229c0254",
                StandardCharsets.UTF_8,
                ',',
                '\"',
                1
        );
        this.localityService = localityService;
    }

    @Override
    protected DepartementDeFrance parse(String[] rowValues) throws Exception {
        DepartementDeFrance data = new DepartementDeFrance();
        data.setCodeDepartement(rowValues[0]);
        data.setNomDepartement(rowValues[1]);
        data.setCodeRegion(rowValues[2]);
        data.setNomRegion(rowValues[3]);
        return data;
    }

    @Override
    protected boolean select(DepartementDeFrance value, LocalDate date) {
        return true;
    }

    @Override
    protected void process(DepartementDeFrance value) {
        localityService.safeUpdateRegion(
                new Region(value.getCodeRegion(), value.getNomRegion())
        );
        localityService.findRegionByCode(value.getCodeRegion())
                .ifPresent(region ->
                        localityService.safeUpdateDepartement(
                                new Department(value.getCodeDepartement(), value.getNomDepartement(), region)
                        ));

    }
}
