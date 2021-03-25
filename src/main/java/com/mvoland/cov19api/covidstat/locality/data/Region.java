package com.mvoland.cov19api.covidstat.locality.data;


import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
                ", regionName='" + regionName +
                '}';
    }
}
