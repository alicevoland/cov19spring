package com.mvoland.cov19api.datagouvfr.data.repository;

import com.mvoland.cov19api.datagouvfr.data.entity.DonneesHospitalieresClasseAgeCovid19Entity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DonneesHospitalieresClasseAgeCovid19Repository {

    private DataGouvCsvBackend<DonneesHospitalieresClasseAgeCovid19Entity> backend;

    public DonneesHospitalieresClasseAgeCovid19Repository() {
        this.backend = new DataGouvCsvBackend<>(
                rowValues -> {
                    DonneesHospitalieresClasseAgeCovid19Entity donneesHospitalieresClasseAgeCovid19Entity = new DonneesHospitalieresClasseAgeCovid19Entity();
                    donneesHospitalieresClasseAgeCovid19Entity.setReg(rowValues[0]);
                    donneesHospitalieresClasseAgeCovid19Entity.setCl_age90(rowValues[1]);
                    donneesHospitalieresClasseAgeCovid19Entity.setJour(rowValues[2]);
                    donneesHospitalieresClasseAgeCovid19Entity.setHosp(Integer.parseInt((rowValues[3])));
                    donneesHospitalieresClasseAgeCovid19Entity.setRea(Integer.parseInt((rowValues[4])));
                    donneesHospitalieresClasseAgeCovid19Entity.setRad(Integer.parseInt((rowValues[5])));
                    donneesHospitalieresClasseAgeCovid19Entity.setDc(Integer.parseInt((rowValues[6])));
                    return donneesHospitalieresClasseAgeCovid19Entity;
                },
                "https://www.data.gouv.fr/fr/datasets/r/08c18e08-6780-452d-9b8c-ae244ad529b3");
    }

    public List<DonneesHospitalieresClasseAgeCovid19Entity> findAll() {
        return backend.getOrFetch();
    }

}
