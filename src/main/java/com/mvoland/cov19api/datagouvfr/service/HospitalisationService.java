package com.mvoland.cov19api.datagouvfr.service;

import com.mvoland.cov19api.data.entity.AgeGroup;
import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.datagouvfr.data.repository.CovidHospitIncidRegRepository;
import com.mvoland.cov19api.datagouvfr.data.repository.DonneesHospitalieresClasseAgeCovid19Repository;
import com.mvoland.cov19api.datagouvfr.data.repository.DonneesHospitalieresCovid19Repository;
import com.mvoland.cov19api.datagouvfr.data.repository.DonneesHospitalieresNouveauxCovid19Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HospitalisationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HospitalisationService.class);

    private final CovidHospitIncidRegRepository covidHospitIncidRegRepository;
    private final DonneesHospitalieresCovid19Repository donneesHospitalieresCovid19Repository;
    private final DonneesHospitalieresNouveauxCovid19Repository donneesHospitalieresNouveauxCovid19Repository;
    private final DonneesHospitalieresClasseAgeCovid19Repository donneesHospitalieresClasseAgeCovid19Repository;

    public HospitalisationService(CovidHospitIncidRegRepository covidHospitIncidRegRepository,
                                  DonneesHospitalieresCovid19Repository donneesHospitalieresCovid19Repository,
                                  DonneesHospitalieresNouveauxCovid19Repository donneesHospitalieresNouveauxCovid19Repository,
                                  DonneesHospitalieresClasseAgeCovid19Repository donneesHospitalieresClasseAgeCovid19Repository) {
        this.covidHospitIncidRegRepository = covidHospitIncidRegRepository;
        this.donneesHospitalieresCovid19Repository = donneesHospitalieresCovid19Repository;
        this.donneesHospitalieresNouveauxCovid19Repository = donneesHospitalieresNouveauxCovid19Repository;
        this.donneesHospitalieresClasseAgeCovid19Repository = donneesHospitalieresClasseAgeCovid19Repository;
    }


    public List<RegionalIntensiveCareAdmission> getAllRegionalIntensiveCareAdmissions() {
        List<RegionalIntensiveCareAdmission> regionalIntensiveCareAdmissions = new ArrayList<>();
        covidHospitIncidRegRepository.findAll().forEach(
                covidHospitIncidRegEntity -> {
                    regionalIntensiveCareAdmissions.add(
                            new RegionalIntensiveCareAdmission(
                                    covidHospitIncidRegEntity.getNumReg(),
                                    LocalDate.parse(covidHospitIncidRegEntity.getJour(), DateTimeFormatter.ISO_LOCAL_DATE),
                                    covidHospitIncidRegEntity.getIncid_rea()
                            )
                    );
                }
        );
        return regionalIntensiveCareAdmissions;
    }

    private Map<Integer, Region> getRegionMap() {
        Map<Integer, Region> regions = new HashMap<>();
        covidHospitIncidRegRepository.findAll().forEach(
                covidHospitIncidRegEntity -> {
                    Integer regionNumber = covidHospitIncidRegEntity.getNumReg();
                    String regionName = covidHospitIncidRegEntity.getNomReg();
                    if (regions.containsKey(regionNumber)) {
                        if (!regions.get(regionNumber).getRegionName().equals(regionName)) {
                            LOGGER.warn("Inconsistent regionName num {} name {} other name {}", regionNumber, regionName, regions.get(regionNumber).getRegionName());
                        }
                    } else {
                        regions.put(regionNumber, new Region(regionNumber, regionName));
                    }
                }
        );
        return regions;
    }

    public List<Region> getAllRegions() {
        return getRegionMap().entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<RegionalHospitalisation> getAllRegionalHospitalisations() {
        List<RegionalHospitalisation> regionalHospitalisations = new ArrayList<>();
        donneesHospitalieresClasseAgeCovid19Repository.findAll().forEach(
                entity -> {
                    regionalHospitalisations.add(
                            new RegionalHospitalisation(
                                    Integer.parseInt(entity.getReg()),
                                    LocalDate.parse(entity.getJour(), DateTimeFormatter.ISO_LOCAL_DATE),
                                    AgeGroup.fromValue(Integer.parseInt(entity.getCl_age90())),
                                    entity.getHosp(),
                                    entity.getRea(),
                                    entity.getRad(),
                                    entity.getDc()
                            )
                    );
                }
        );
        return regionalHospitalisations;
    }

}
