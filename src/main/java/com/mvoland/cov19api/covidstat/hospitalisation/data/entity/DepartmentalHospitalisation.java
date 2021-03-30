package com.mvoland.cov19api.covidstat.hospitalisation.data.entity;

import com.mvoland.cov19api.common.type.Sex;
import com.mvoland.cov19api.covidstat.locality.data.Department;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(indexes = @Index(name = "index", columnList = "department_id, noticeDate, sex", unique = true))
@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class DepartmentalHospitalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NonNull @JoinColumn
    private Department department;

    @NonNull@Column
    private LocalDate noticeDate;

    @Enumerated(EnumType.STRING)
    @NonNull@Column
    private Sex sex;

    @NonNull@Column
    private Integer currentHospitalizedCount;

    @NonNull@Column
    private Integer currentIntensiveCareCount;

    @NonNull@Column
    private Integer currentRadiationCount;

    @NonNull@Column
    private Integer currentDeathCount;

}
