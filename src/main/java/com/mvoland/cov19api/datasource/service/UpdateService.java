package com.mvoland.cov19api.datasource.service;

import com.mvoland.cov19api.datasource.common.DataSource;
import com.mvoland.cov19api.datasource.common.UpdateRequest;
import com.mvoland.cov19api.datasource.depdefr.DepartementDeFranceSource;
import com.mvoland.cov19api.datasource.hospdata.CovidHospitIncidRegSource;
import com.mvoland.cov19api.datasource.hospdata.DonneesHospitalieresClasseAgeCovid19Source;
import com.mvoland.cov19api.datasource.hospdata.DonneesHospitalieresCovid19Source;
import com.mvoland.cov19api.datasource.hospdata.DonneesHospitalieresNouveauxCovid19Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class UpdateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateService.class);

    private final List<DataSource> dataSources;

    private Thread updateThread;

    @Autowired
    public UpdateService(
            DepartementDeFranceSource departementDeFranceSource,
            CovidHospitIncidRegSource covidHospitIncidRegSource,
            DonneesHospitalieresClasseAgeCovid19Source donneesHospitalieresClasseAgeCovid19Source,
            DonneesHospitalieresNouveauxCovid19Source donneesHospitalieresNouveauxCovid19Source,
            DonneesHospitalieresCovid19Source donneesHospitalieresCovid19Source
    ) {
        this(Arrays.asList(
                departementDeFranceSource,
                covidHospitIncidRegSource,
                donneesHospitalieresClasseAgeCovid19Source,
                donneesHospitalieresNouveauxCovid19Source,
                donneesHospitalieresCovid19Source
        ));
    }

    private UpdateService(
            List<DataSource> dataSources
    ) {
        this.dataSources = dataSources;
        updateThread = null;
    }

    public synchronized UpdateRequest requestFullUpdate() {
        if (updateThread != null) {
            LOGGER.info("Full update rejected");
            return UpdateRequest.rejected("An other update running");
        } else {
            updateThread = new Thread(() -> {
                LOGGER.info("Full update started");
                dataSources.forEach(DataSource::fullUpdate);
                this.updateThread = null;
                LOGGER.info("Full update done");
            });
            updateThread.start();
            return UpdateRequest.accepted();
        }
    }

    public synchronized UpdateRequest requestUpdateDays(int days) {
        LocalDate date = LocalDate.now().minusDays(days);
        if (updateThread != null) {
            LOGGER.info("Update since {} rejected", date);
            return UpdateRequest.rejected("An other update running");
        } else {
            updateThread = new Thread(() -> {
                LOGGER.info("Update since {} STARTED", date);
                dataSources.forEach(dataSource -> dataSource.updateSince(date));
                this.updateThread = null;
                LOGGER.info("Update since {} DONE", date);
            });
            updateThread.start();
            return UpdateRequest.accepted();
        }
    }

}
