package com.mvoland.cov19api.covidstat.locality.data;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "departmentCode", unique = true))
@Data @NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String departmentCode;

    @Column
    @NonNull
    private String departmentName;

    @ManyToOne
    @JoinColumn(
            name = "region_id",
            nullable = false
    )
    @NonNull
    private Region region;
}
