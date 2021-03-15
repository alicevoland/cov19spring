package com.mvoland.cov19api.datagouvfr.data.repository;

import com.mvoland.cov19api.datagouvfr.data.entity.DonneesHospitalieresNouveauxCovid19Entity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DonneesHospitalieresNouveauxCovid19Repository {

    private DataGouvCsvBackend<DonneesHospitalieresNouveauxCovid19Entity> backend;

    public DonneesHospitalieresNouveauxCovid19Repository() {
        this.backend = new DataGouvCsvBackend<>(
                rowValues -> {
                    DonneesHospitalieresNouveauxCovid19Entity donneesHospitalieresNouveauxCovid19Entity = new DonneesHospitalieresNouveauxCovid19Entity();
                    donneesHospitalieresNouveauxCovid19Entity.setDep(rowValues[0]);
                    donneesHospitalieresNouveauxCovid19Entity.setJour(rowValues[1]);
                    donneesHospitalieresNouveauxCovid19Entity.setIncid_hosp(Integer.parseInt(rowValues[2]));
                    donneesHospitalieresNouveauxCovid19Entity.setIncid_rea(Integer.parseInt((rowValues[3])));
                    donneesHospitalieresNouveauxCovid19Entity.setIncid_dc(Integer.parseInt(rowValues[4]));
                    donneesHospitalieresNouveauxCovid19Entity.setIncid_rad(Integer.parseInt((rowValues[5])));
                    return donneesHospitalieresNouveauxCovid19Entity;
                },
                "https://www.data.gouv.fr/fr/datasets/r/6fadff46-9efd-4c53-942a-54aca783c30c");
    }

    public List<DonneesHospitalieresNouveauxCovid19Entity> findAll() {
        return backend.getOrFetch();
    }

}
