package com.faisal.dojoandninjas.repositories;

import com.faisal.dojoandninjas.models.Dojo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DojoRepository extends CrudRepository<Dojo,Long> {

    public List<Dojo> findAll();
}
