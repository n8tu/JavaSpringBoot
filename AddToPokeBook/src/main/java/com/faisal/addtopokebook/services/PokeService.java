package com.faisal.addtopokebook.services;

import com.faisal.addtopokebook.models.Poke;
import com.faisal.addtopokebook.repositories.PokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokeService {
    private final PokeRepository pokeRepository;

    public PokeService(PokeRepository pokeRepository) {
        this.pokeRepository = pokeRepository;
    }

    public List<Poke> getAllPokes(){
        return pokeRepository.findAll();
    }

    public void createPoke(Poke p){
        pokeRepository.save(p);
    }

    public Poke getPoke(long id){
        Optional<Poke> poke = pokeRepository.findById(id);
        if(poke.isPresent()){
            return poke.get();
        }else{
            return null;
        }
    }

    public void updatePoke(long id , Poke p){
        Optional<Poke> poke = pokeRepository.findById(id);
        if(poke.isPresent()){
            poke.get().setExpense_name(p.getExpense_name());
            poke.get().setDescription(p.getDescription());
            poke.get().setAmount(p.getAmount());
            poke.get().setVendor(p.getVendor());
            pokeRepository.save(poke.get());
        }
    }

    public void deletePoke(long id){
        Optional<Poke> poke = pokeRepository.findById(id);
        if(poke.isPresent()){
            pokeRepository.delete(poke.get());
        }
    }
}
