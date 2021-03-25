package com.mvoland.cov19api.covidstat.locality.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(indexes = @Index(columnList = "departmentCode", unique = true))
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departmentCode;
    private String departmentName;

    @ManyToOne
    private Region region;

    public Department() {
    }


    public Department(String departmentCode, String departmentName, Region region) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.region = region;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departementCode) {
        this.departmentCode = departementCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departementName) {
        this.departmentName = departementName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentCode, that.departmentCode) && Objects.equals(departmentName, that.departmentName) && Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentCode, departmentName, region);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentCode='" + departmentCode + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", region=" + region +
                '}';
    }
}
