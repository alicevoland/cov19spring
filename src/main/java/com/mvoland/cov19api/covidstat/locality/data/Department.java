package com.mvoland.cov19api.covidstat.locality.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(indexes = @Index(columnList = "departmentNumber", unique = true))
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer departmentNumber;
    private String departmentName;

    public Department() {
    }


    public Department(Integer departmentNumber, String departmentName) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
    }

    public Integer getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Integer departementNumber) {
        this.departmentNumber = departementNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departementName) {
        this.departmentName = departementName;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "departementNumber=" + departmentNumber +
                ", departementName='" + departmentName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentNumber, that.departmentNumber) && Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentNumber, departmentName);
    }
}
