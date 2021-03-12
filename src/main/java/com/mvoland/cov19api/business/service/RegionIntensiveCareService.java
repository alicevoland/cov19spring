package com.mvoland.cov19api.business.service;

import com.mvoland.cov19api.business.domain.RegionIntensiveCare;
import com.mvoland.cov19api.data.entity.RegionIntensiveCareAdmission;
import com.mvoland.cov19api.data.repository.RegionIntensiveCareAdmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegionIntensiveCareService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionIntensiveCareService.class);

    private final RegionIntensiveCareAdmissionRepository regionIntensiveCareAdmissionRepository;

    @Autowired
    public RegionIntensiveCareService(RegionIntensiveCareAdmissionRepository regionIntensiveCareAdmissionRepository) {
        this.regionIntensiveCareAdmissionRepository = regionIntensiveCareAdmissionRepository;
    }

    private List<RegionIntensiveCare> convert(List<RegionIntensiveCareAdmission> regionIntensiveCareAdmissions) {
        Map<Integer, List<RegionIntensiveCareAdmission>> intensiveCareMap = new HashMap<>();
        for (RegionIntensiveCareAdmission admission : regionIntensiveCareAdmissions) {
            if (!intensiveCareMap.containsKey(admission.getNumReg())) {
                intensiveCareMap.put(admission.getNumReg(), new LinkedList<>());
            }
            intensiveCareMap.get(admission.getNumReg()).add(admission);
        }

        List<RegionIntensiveCare> regionIntensiveCares = new ArrayList<>();
        intensiveCareMap.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .forEach(admissionList -> {
                    SortedMap<LocalDate, Integer> admissionMap = new TreeMap<>();
                    admissionList.forEach(admission -> {
                        LocalDate date = LocalDate.parse(admission.getJour(), DateTimeFormatter.ISO_LOCAL_DATE);
                        admissionMap.put(date, admissionMap.getOrDefault(date, 0) + admission.getIncid_rea());
                    });
                    RegionIntensiveCare regionIntensiveCare = new RegionIntensiveCare(
                            admissionList.get(0).getNomReg(),
                            admissionList.get(0).getNumReg(),
                            admissionMap
                    );
                    regionIntensiveCares.add(regionIntensiveCare);
                });
        return regionIntensiveCares;
    }

    public List<RegionIntensiveCare> getAllRegionIntensiveCares() {
        return convert(this.regionIntensiveCareAdmissionRepository.findAll());
    }

    public List<RegionIntensiveCare> getRegionIntensiveCaresFor(String date, String region) {
        return convert(
                this.regionIntensiveCareAdmissionRepository.findAll().stream()
                        .filter(admission -> date == null || admission.getJour().equalsIgnoreCase(date))
                        .filter(admission -> region == null || admission.getNomReg().equalsIgnoreCase(region))
                        .collect(Collectors.toList())
        );
    }
}
