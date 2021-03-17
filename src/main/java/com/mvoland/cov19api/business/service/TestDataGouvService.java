package com.mvoland.cov19api.business.service;

import com.mvoland.cov19api.datagouvfr.HospDataDatabaseUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TestDataGouvService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataGouvService.class);
    private final HospDataDatabaseUpdater hospDataDatabaseUpdater;


    @Autowired
    public TestDataGouvService(
            HospDataDatabaseUpdater hospDataDatabaseUpdater) {
        this.hospDataDatabaseUpdater = hospDataDatabaseUpdater;
    }

    @Transactional
    public void test() {
    hospDataDatabaseUpdater.freshUpdate();

    }
}
