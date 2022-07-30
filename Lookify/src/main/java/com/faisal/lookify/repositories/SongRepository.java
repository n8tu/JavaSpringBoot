package com.faisal.lookify.repositories;

import com.faisal.lookify.models.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SongRepository extends CrudRepository<Song,Long> {

    public ArrayList<Song> findAll();

    public ArrayList<Song> findAllByArtist(String artist);

    public ArrayList<Song> findTop10ByOrderByRateDesc();


}
