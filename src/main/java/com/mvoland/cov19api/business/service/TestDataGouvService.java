package com.mvoland.cov19api.business.service;

import com.mvoland.cov19api.datagouvfr.data.entity.CovidHospitIncidRegEntity;
import com.mvoland.cov19api.datagouvfr.data.entity.DonneesHospitalieresClasseAgeCovid19Entity;
import com.mvoland.cov19api.datagouvfr.data.entity.DonneesHospitalieresCovid19Entity;
import com.mvoland.cov19api.datagouvfr.data.entity.DonneesHospitalieresNouveauxCovid19Entity;
import com.mvoland.cov19api.datagouvfr.data.repository.CovidHospitIncidRegRepository;
import com.mvoland.cov19api.datagouvfr.data.repository.DonneesHospitalieresClasseAgeCovid19Repository;
import com.mvoland.cov19api.datagouvfr.data.repository.DonneesHospitalieresCovid19Repository;
import com.mvoland.cov19api.datagouvfr.data.repository.DonneesHospitalieresNouveauxCovid19Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataGouvService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataGouvService.class);

    private final CovidHospitIncidRegRepository covidHospitIncidRegRepository;
    private final DonneesHospitalieresCovid19Repository donneesHospitalieresCovid19Repository;
    private final DonneesHospitalieresNouveauxCovid19Repository donneesHospitalieresNouveauxCovid19Repository;
    private final DonneesHospitalieresClasseAgeCovid19Repository donneesHospitalieresClasseAgeCovid19Repository;

    public TestDataGouvService(
            CovidHospitIncidRegRepository covidHospitIncidRegRepository,
            DonneesHospitalieresCovid19Repository donneesHospitalieresCovid19Repository,
            DonneesHospitalieresNouveauxCovid19Repository donneesHospitalieresNouveauxCovid19Repository,
            DonneesHospitalieresClasseAgeCovid19Repository donneesHospitalieresClasseAgeCovid19Repository) {
        this.covidHospitIncidRegRepository = covidHospitIncidRegRepository;
        this.donneesHospitalieresCovid19Repository = donneesHospitalieresCovid19Repository;
        this.donneesHospitalieresNouveauxCovid19Repository = donneesHospitalieresNouveauxCovid19Repository;
        this.donneesHospitalieresClasseAgeCovid19Repository = donneesHospitalieresClasseAgeCovid19Repository;
    }

    public void test() {
        List<CovidHospitIncidRegEntity> covidHospitIncidRegEntities = this.covidHospitIncidRegRepository.findAll();
        System.out.println("CovidHospitIncidRegEntity");
        covidHospitIncidRegEntities.stream().limit(2).forEach(System.out::println);
        System.out.println("-- ");

        List<DonneesHospitalieresCovid19Entity> donneesHospitalieresCovid19Entities = this.donneesHospitalieresCovid19Repository.findAll();
        System.out.println("DonneesHospitalieresCovid19Entity");
        donneesHospitalieresCovid19Entities.stream().limit(2).forEach(System.out::println);
        System.out.println("-- ");

        List<DonneesHospitalieresClasseAgeCovid19Entity> donneesHospitalieresClasseAgeCovid19Entities = this.donneesHospitalieresClasseAgeCovid19Repository.findAll();
        System.out.println("DonneesHospitalieresClasseAgeCovid19Entity");
        donneesHospitalieresClasseAgeCovid19Entities.stream().limit(2).forEach(System.out::println);
        System.out.println("-- ");

        List<DonneesHospitalieresNouveauxCovid19Entity> donneesHospitalieresNouveauxCovid19Entities = this.donneesHospitalieresNouveauxCovid19Repository.findAll();
        System.out.println("DonneesHospitalieresNouveauxCovid19Entity");
        donneesHospitalieresNouveauxCovid19Entities.stream().limit(2).forEach(System.out::println);
        System.out.println("-- ");
    }
}
