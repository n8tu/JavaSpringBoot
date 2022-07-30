package com.faisal.lookify.services;

import com.faisal.lookify.models.Song;
import com.faisal.lookify.repositories.SongRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public ArrayList<Song> AllSongs(){
        return songRepository.findAll();
    }

    public Song createSong(Song s){
        return songRepository.save(s);
    }

    public Song findSong(long id){
        Optional<Song> song = songRepository.findById(id);
        if(song.isPresent()){
            return song.get();
        }else{
            return null;
        }
    }

    public void updateSong(long id , Song s){
        Optional<Song> song = songRepository.findById(id);
        if(song.isPresent()){
            song.get().setArtist(s.getArtist());
            song.get().setRate(s.getRate());
            song.get().setTitle(s.getTitle());
            songRepository.save(song.get());
        }
    }

    public void deleteSong(long id){
        Optional<Song> song = songRepository.findById(id);
        if(song.isPresent()){
            songRepository.delete(song.get());
        }
    }

    public ArrayList<Song> artistSongs(String artist){
        return songRepository.findAllByArtist(artist);
    }

    public ArrayList<Song> topTenSongs(){
        return songRepository.findTop10ByOrderByRateDesc();
    }
}
