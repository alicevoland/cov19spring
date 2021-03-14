package com.mvoland.cov19api.business.service;

import com.mvoland.cov19api.data.entity.CovidHospitIncidRegEntity;
import com.mvoland.cov19api.data.entity.DonneesHospitalieresCovid19Entity;
import com.mvoland.cov19api.data.repository.CovidHospitIncidRegRepository;
import com.mvoland.cov19api.data.repository.DonneesHospitalieresCovid19Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataGouvService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataGouvService.class);

    private final CovidHospitIncidRegRepository covidHospitIncidRegRepository;
    private final DonneesHospitalieresCovid19Repository donneesHospitalieresCovid19Repository;

    @Autowired
    public TestDataGouvService(
            CovidHospitIncidRegRepository covidHospitIncidRegRepository,
            DonneesHospitalieresCovid19Repository donneesHospitalieresCovid19Repository) {
        this.covidHospitIncidRegRepository = covidHospitIncidRegRepository;
        this.donneesHospitalieresCovid19Repository = donneesHospitalieresCovid19Repository;
    }

    public void test() {
        List<CovidHospitIncidRegEntity> covidHospitIncidRegEntities = this.covidHospitIncidRegRepository.findAll();
        System.out.println("CovidHospitIncidRegEntity");
        covidHospitIncidRegEntities.stream().limit(5).forEach(System.out::println);
        System.out.println("-- ");
        List<DonneesHospitalieresCovid19Entity> donneesHospitalieresCovid19Entities = this.donneesHospitalieresCovid19Repository.findAll();
        System.out.println("DonneesHospitalieresCovid19Entity");
        donneesHospitalieresCovid19Entities.stream().limit(5).forEach(System.out::println);
    }
}
