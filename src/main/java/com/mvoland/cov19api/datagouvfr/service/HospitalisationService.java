package com.mvoland.cov19api.datagouvfr.service;

import com.mvoland.cov19api.data.entity.AgeGroup;
import com.mvoland.cov19api.data.entity.Region;
import com.mvoland.cov19api.data.entity.RegionalHospitalisation;
import com.mvoland.cov19api.data.entity.RegionalIntensiveCareAdmission;
import com.mvoland.cov19api.datagouvfr.hospdata.CovidHospitIncidReg;
import com.mvoland.cov19api.datagouvfr.hospdata.DonneesHospitalieresClasseAgeCovid19;
import com.mvoland.cov19api.datagouvfr.hospdata.DonneesHospitalieresCovid19;
import com.mvoland.cov19api.datagouvfr.hospdata.DonneesHospitalieresNouveauxCovid19;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HospitalisationService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(HospitalisationService.class);
//
//    private final CovidHospitIncidReg covidHospitIncidReg;
//    private final DonneesHospitalieresCovid19 donneesHospitalieresCovid19;
//    private final DonneesHospitalieresNouveauxCovid19 donneesHospitalieresNouveauxCovid19;
//    private final DonneesHospitalieresClasseAgeCovid19 donneesHospitalieresClasseAgeCovid19;
//
//    public HospitalisationService(CovidHospitIncidReg covidHospitIncidReg,
//                                  DonneesHospitalieresCovid19 donneesHospitalieresCovid19,
//                                  DonneesHospitalieresNouveauxCovid19 donneesHospitalieresNouveauxCovid19,
//                                  DonneesHospitalieresClasseAgeCovid19 donneesHospitalieresClasseAgeCovid19) {
//        this.covidHospitIncidReg = covidHospitIncidReg;
//        this.donneesHospitalieresCovid19 = donneesHospitalieresCovid19;
//        this.donneesHospitalieresNouveauxCovid19 = donneesHospitalieresNouveauxCovid19;
//        this.donneesHospitalieresClasseAgeCovid19 = donneesHospitalieresClasseAgeCovid19;
//    }
//
//
//    public List<RegionalIntensiveCareAdmission> getAllRegionalIntensiveCareAdmissions() {
//        List<RegionalIntensiveCareAdmission> regionalIntensiveCareAdmissions = new ArrayList<>();
//        covidHospitIncidReg.findAll().forEach(
//                covidHospitIncidRegEntity -> {
//                    regionalIntensiveCareAdmissions.add(
//                            new RegionalIntensiveCareAdmission(
//                                    covidHospitIncidRegEntity.getNumReg(),
//                                    LocalDate.parse(covidHospitIncidRegEntity.getJour(), DateTimeFormatter.ISO_LOCAL_DATE),
//                                    covidHospitIncidRegEntity.getIncid_rea()
//                            )
//                    );
//                }
//        );
//        return regionalIntensiveCareAdmissions;
//    }
//
//    private Map<Integer, Region> getRegionMap() {
//        Map<Integer, Region> regions = new HashMap<>();
//        covidHospitIncidReg.findAll().forEach(
//                covidHospitIncidRegEntity -> {
//                    Integer regionNumber = covidHospitIncidRegEntity.getNumReg();
//                    String regionName = covidHospitIncidRegEntity.getNomReg();
//                    if (regions.containsKey(regionNumber)) {
//                        if (!regions.get(regionNumber).getRegionName().equals(regionName)) {
//                            LOGGER.warn("Inconsistent regionName num {} name {} other name {}", regionNumber, regionName, regions.get(regionNumber).getRegionName());
//                        }
//                    } else {
//                        regions.put(regionNumber, new Region(regionNumber, regionName));
//                    }
//                }
//        );
//        return regions;
//    }
//
//    public List<Region> getAllRegions() {
//        return getRegionMap().entrySet().stream()
//                .sorted(Comparator.comparingInt(Map.Entry::getKey))
//                .map(Map.Entry::getValue)
//                .collect(Collectors.toList());
//    }
//
//    public List<RegionalHospitalisation> getAllRegionalHospitalisations() {
//        List<RegionalHospitalisation> regionalHospitalisations = new ArrayList<>();
//        donneesHospitalieresClasseAgeCovid19.findAll().forEach(
//                entity -> {
//                    regionalHospitalisations.add(
//                            new RegionalHospitalisation(
//                                    Integer.parseInt(entity.getReg()),
//                                    LocalDate.parse(entity.getJour(), DateTimeFormatter.ISO_LOCAL_DATE),
//                                    AgeGroup.fromValue(Integer.parseInt(entity.getCl_age90())),
//                                    entity.getHosp(),
//                                    entity.getRea(),
//                                    entity.getRad(),
//                                    entity.getDc()
//                            )
//                    );
//                }
//        );
//        return regionalHospitalisations;
//    }

}
