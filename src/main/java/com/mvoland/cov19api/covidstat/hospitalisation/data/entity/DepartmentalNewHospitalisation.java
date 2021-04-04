package com.mvoland.cov19api.covidstat.hospitalisation.data.entity;

import com.mvoland.cov19api.covidstat.locality.data.entity.Department;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(indexes = @Index(name = "index", columnList = "department_id, noticeDate", unique = true))
@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class DepartmentalNewHospitalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NonNull @JoinColumn
    private Department department;

    @NonNull @Column
    private LocalDate noticeDate;

    @NonNull @Column
    private Integer newHospitalizedCount;

    @NonNull @Column
    private Integer newIntensiveCareCount;

    @NonNull @Column
    private Integer newRadiationCount;

    @NonNull @Column
    private Integer newDeathCount;

}
