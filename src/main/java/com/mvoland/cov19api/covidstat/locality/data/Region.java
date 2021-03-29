package com.mvoland.cov19api.covidstat.locality.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(indexes = @Index(name = "index", columnList = "regionCode", unique = true))
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String regionCode;

    @Column
    private String regionName;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "region"
    )
    @JsonIgnore
    private Set<Department> departments = new HashSet<>();

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Region(String regionCode, String regionName) {
        this.setRegionCode(regionCode);
        this.setRegionName(regionName);
    }

    public Region() {

    }

    public Long getId() {
        return id;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(regionCode, region.regionCode) && Objects.equals(regionName, region.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionCode, regionName);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", regionCode='" + regionCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", departments=" + departments.stream().map(d -> d.getDepartmentCode()).collect(Collectors.joining(", ")) +
                '}';
    }
}
