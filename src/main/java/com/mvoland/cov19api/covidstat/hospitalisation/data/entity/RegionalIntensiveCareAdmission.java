package com.mvoland.cov19api.covidstat.hospitalisation.data.entity;

import com.mvoland.cov19api.covidstat.locality.data.entity.Region;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(indexes = @Index(name = "index", columnList = "region_id, noticeDate", unique = true))
@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class RegionalIntensiveCareAdmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NonNull @JoinColumn
    private Region region;

    @NonNull @Column
    private LocalDate noticeDate;

    @NonNull @Column
    private Integer intensiveCareAdmissionCount;

}
