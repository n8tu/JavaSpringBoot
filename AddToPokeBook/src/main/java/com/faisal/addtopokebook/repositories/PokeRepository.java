package com.faisal.addtopokebook.repositories;

import com.faisal.addtopokebook.models.Poke;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokeRepository extends CrudRepository<Poke,Long> {
    public List<Poke> findAll();
}
