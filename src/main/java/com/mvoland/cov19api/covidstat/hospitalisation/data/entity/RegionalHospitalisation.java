package com.mvoland.cov19api.covidstat.hospitalisation.data.entity;

import com.mvoland.cov19api.common.type.AgeGroup;
import com.mvoland.cov19api.covidstat.locality.data.entity.Region;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(indexes = @Index(name = "index", columnList = "region_id, noticeDate, ageGroup", unique = true))
@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class RegionalHospitalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NonNull @JoinColumn
    private Region region;


    @NonNull @Column
    private LocalDate noticeDate;

    @Enumerated(EnumType.STRING)
    @NonNull @Column
    private AgeGroup ageGroup;

    @NonNull @Column
    private Integer currentHospitalizedCount;

    @NonNull @Column
    private Integer currentIntensiveCareCount;

    @NonNull @Column
    private Integer currentRadiationCount;

    @NonNull @Column
    private Integer currentDeathCount;

}
