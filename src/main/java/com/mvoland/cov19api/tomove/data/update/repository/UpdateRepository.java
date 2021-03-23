package com.mvoland.cov19api.tomove.data.update.repository;

import com.mvoland.cov19api.tomove.data.update.entity.UpdateRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateRepository extends CrudRepository<UpdateRequest, Long> {
    List<UpdateRequest> findByDataSource(String dataSource);
    List<UpdateRequest> findAll();

}
