package com.mvoland.cov19api.covidstat.locality.model;

import com.mvoland.cov19api.covidstat.locality.data.entity.Department;
import com.mvoland.cov19api.covidstat.locality.data.entity.Region;

import java.util.*;
import java.util.stream.Collectors;

public class RegionWrapper {
    private final Region region;
    private final List<Department> departments;

    public RegionWrapper(Region region, List<Department> departments) {
        this.region = region;
        this.departments = Collections.unmodifiableList(departments);
    }

    public Region getRegion() {
        return region;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public static List<RegionWrapper> createRegionWrapperList(
            Iterable<Department> departments
    ) {
        Map<Region, List<Department>> departementsByRegion = new HashMap<>();

        for (var department : departments) {
            if (!departementsByRegion.containsKey(department.getRegion())) {
                departementsByRegion.put(department.getRegion(), new LinkedList<>());
            }
            departementsByRegion.get(department.getRegion()).add(department);
        }

        return departementsByRegion.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().getRegionCode()))
                .map(entry -> new RegionWrapper(
                        entry.getKey(),
                        entry.getValue().stream()
                                .sorted(Comparator.comparing(department -> department.getDepartmentCode()))
                                .collect(Collectors.toUnmodifiableList())))
                .collect(Collectors.toUnmodifiableList());
    }

}
