package com.mvoland.cov19api.data.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(indexes = @Index(columnList = "regionNumber", unique = true))
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer regionNumber;

    private String regionName;

    public Region() {
    }

    public Region(Integer regionNumber, String regionName) {
        this.setRegionNumber(regionNumber);
        this.setRegionName(regionName);
    }

    public Integer getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(Integer regionNumber) {
        this.regionNumber = regionNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "Region{" +
                "regionNumber=" + regionNumber +
                ", regionName='" + regionName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(regionNumber, region.regionNumber) && Objects.equals(regionName, region.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionNumber, regionName);
    }
}
