package com.faisal.dojoandninjas.services;

import com.faisal.dojoandninjas.models.Ninja;
import com.faisal.dojoandninjas.repositories.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public List<Ninja> allNinjas(){
        return ninjaRepository.findAll();
    }

    public void createNinja(Ninja n){
        ninjaRepository.save(n);
    }
}
