package com.faisal.dojoandninjas.repositories;

import com.faisal.dojoandninjas.models.Ninja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NinjaRepository extends CrudRepository<Ninja,Long> {

    public List<Ninja> findAll();
}
