package com.faisal.languages.repositories;

import com.faisal.languages.models.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends CrudRepository<Language,Long> {
    public List<Language> findAll();
}
