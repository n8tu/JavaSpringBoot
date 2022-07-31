package com.faisal.dojoandninjas.services;

import com.faisal.dojoandninjas.models.Dojo;
import com.faisal.dojoandninjas.repositories.DojoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DojoService {

    private final DojoRepository dojoRepository;

    public DojoService(DojoRepository dojoRepository) {
        this.dojoRepository = dojoRepository;
    }

    public List<Dojo> allDojos(){
        return dojoRepository.findAll();
    }

    public void createDojo(Dojo d){
        dojoRepository.save(d);
    }

    public Dojo findDojo(long id){
        Optional<Dojo> dojo = dojoRepository.findById(id);
        if(dojo.isPresent()){
            return dojo.get();
        }else{
            return null;
        }
    }
}
