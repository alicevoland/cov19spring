package com.mvoland.cov19api.datagouvfr.depdefr.service;

import com.mvoland.cov19api.datagouvfr.common.UpdateService;
import com.mvoland.cov19api.datagouvfr.depdefr.data.DepartementDeFranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepDeFrUpdateService extends UpdateService {

    @Autowired
    public DepDeFrUpdateService(
            DepartementDeFranceService departementDeFranceService
    ) {
        super(departementDeFranceService);
    }
}
