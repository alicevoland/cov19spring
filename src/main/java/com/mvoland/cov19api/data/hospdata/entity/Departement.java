package com.mvoland.cov19api.data.hospdata.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(indexes = @Index(columnList = "departementNumber", unique = true))
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer departementNumber;
    private String departementName;

    public Departement() {
    }


    public Departement(Integer departementNumber, String departementName) {
        this.departementNumber = departementNumber;
        this.departementName = departementName;
    }

    public Integer getDepartementNumber() {
        return departementNumber;
    }

    public void setDepartementNumber(Integer departementNumber) {
        this.departementNumber = departementNumber;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "departementNumber=" + departementNumber +
                ", departementName='" + departementName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departement that = (Departement) o;
        return Objects.equals(departementNumber, that.departementNumber) && Objects.equals(departementName, that.departementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departementNumber, departementName);
    }
}
