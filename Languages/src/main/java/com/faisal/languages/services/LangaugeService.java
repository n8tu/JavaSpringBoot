package com.faisal.languages.services;

import com.faisal.languages.models.Language;
import com.faisal.languages.repositories.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LangaugeService {

    private final LanguageRepository languageRepository;

    public LangaugeService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> allLanguages(){
        return languageRepository.findAll();
    }
    public void createLanguage(Language l){
        languageRepository.save(l);
    }

    public Language findLanguage(long id){
        Optional<Language> language = languageRepository.findById(id);
        if(language.isPresent()){
            return language.get();
        }else{
            return null;
        }
    }

    public void deleteLanguage(long id){
        Optional<Language> language = languageRepository.findById(id);
        if(language.isPresent()){
            languageRepository.delete(language.get());
        }
    }

    public void updateLanguage(long id , Language l){
        Optional<Language> language = languageRepository.findById(id);
        if(language.isPresent()){
            language.get().setName(l.getName());
            language.get().setCreator(l.getCreator());
            language.get().setCurrentVersion(l.getCurrentVersion());
            languageRepository.save(language.get());
        }
    }
}
