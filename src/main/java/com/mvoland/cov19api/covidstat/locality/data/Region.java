package com.mvoland.cov19api.covidstat.locality.data;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(name = "index", columnList = "regionCode", unique = true))

@Data
@NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor

public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String regionCode;

    @Column
    @NonNull
    private String regionName;
    
}
