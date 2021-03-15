package com.mvoland.cov19api.datagouvfr.data.repository;

import com.mvoland.cov19api.datagouvfr.data.entity.DonneesHospitalieresCovid19Entity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DonneesHospitalieresCovid19Repository {

    private DataGouvCsvBackend<DonneesHospitalieresCovid19Entity> backend;

    public DonneesHospitalieresCovid19Repository() {
        this.backend = new DataGouvCsvBackend<>(
                rowValues -> {
                    DonneesHospitalieresCovid19Entity donneesHospitalieresCovid19Entity = new DonneesHospitalieresCovid19Entity();
                    donneesHospitalieresCovid19Entity.setDep(rowValues[0]);
                    donneesHospitalieresCovid19Entity.setSexe(rowValues[1]);
                    donneesHospitalieresCovid19Entity.setJour(rowValues[2]);
                    donneesHospitalieresCovid19Entity.setHosp(Integer.parseInt((rowValues[3])));
                    donneesHospitalieresCovid19Entity.setRea(Integer.parseInt((rowValues[4])));
                    donneesHospitalieresCovid19Entity.setRad(Integer.parseInt((rowValues[5])));
                    donneesHospitalieresCovid19Entity.setDc(Integer.parseInt((rowValues[6])));
                    return donneesHospitalieresCovid19Entity;
                },
                "https://www.data.gouv.fr/fr/datasets/r/63352e38-d353-4b54-bfd1-f1b3ee1cabd7");
    }

    public List<DonneesHospitalieresCovid19Entity> findAll() {
        return backend.getOrFetch();
    }

}
