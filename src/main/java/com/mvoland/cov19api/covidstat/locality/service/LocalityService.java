package com.mvoland.cov19api.covidstat.locality.service;

import com.mvoland.cov19api.covidstat.locality.data.DepartementRepository;
import com.mvoland.cov19api.covidstat.locality.data.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalityService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private DepartementRepository departementRepository;


}
