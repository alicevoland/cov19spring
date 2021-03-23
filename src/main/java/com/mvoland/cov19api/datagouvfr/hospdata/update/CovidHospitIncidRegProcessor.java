package com.mvoland.cov19api.datagouvfr.hospdata.update;

import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import com.mvoland.cov19api.covidstat.locality.service.LocalityService;
import com.mvoland.cov19api.datagouvfr.common.UpdateProcessor;
import com.mvoland.cov19api.datagouvfr.hospdata.data.CovidHospitIncidReg;
import org.springframework.beans.factory.annotation.Autowired;

public class CovidHospitIncidRegProcessor implements UpdateProcessor<CovidHospitIncidReg> {

    @Autowired
    private LocalityService localityService;



    @Override
    public void process(CovidHospitIncidReg value) {

    }
}
