package com.mvoland.cov19api.datagouvfr.hospdata.update.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateRepository extends CrudRepository<UpdateRequest, Long> {
    List<UpdateRequest> findByDataSource(String dataSource);
    List<UpdateRequest> findAll();

}
