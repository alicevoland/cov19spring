package com.mvoland.cov19api.datagouvfr.data.repository;

import com.mvoland.cov19api.datagouvfr.data.entity.CovidHospitIncidRegEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CovidHospitIncidRegRepository {

    private DataGouvCsvBackend<CovidHospitIncidRegEntity> backend;

    public CovidHospitIncidRegRepository() {
        this.backend = new DataGouvCsvBackend<>(
                rowValues -> {
                    CovidHospitIncidRegEntity covidHospitIncidRegEntity = new CovidHospitIncidRegEntity();
                    covidHospitIncidRegEntity.setJour(rowValues[0]);
                    covidHospitIncidRegEntity.setNomReg(rowValues[1]);
                    covidHospitIncidRegEntity.setNumReg(Integer.parseInt(rowValues[2]));
                    covidHospitIncidRegEntity.setIncid_rea(Integer.parseInt((rowValues[3])));
                    return covidHospitIncidRegEntity;
                },
                "https://www.data.gouv.fr/fr/datasets/r/a1466f7f-4ece-4158-a373-f5d4db167eb0");
    }

    public List<CovidHospitIncidRegEntity> findAll() {
        return backend.getOrFetch();
    }

}
