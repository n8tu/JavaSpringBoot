package com.faisal.lookify.controllers;

import com.faisal.lookify.models.Song;
import com.faisal.lookify.services.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping("/")
    public String index(){
        return "index.jsp";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(
            Model model
    ){
        model.addAttribute("songs",songService.AllSongs());
        return "dashboard.jsp";
    }

    @RequestMapping(value = "/search" ,method = RequestMethod.POST)
    public String search(
            @RequestParam(value = "artist") String artist
    ){
        return "redirect:/search/"+ artist;
    }

    @RequestMapping(value = "/search/{artist}" , method = RequestMethod.GET)
    public String showArtistSongs(
            @PathVariable(value = "artist") String artist,
            Model model
    ){
        model.addAttribute("songs",songService.artistSongs(artist));
        model.addAttribute("artist",artist);
        return "search.jsp";
    }

    @RequestMapping(value = "/songs/{id}/delete" , method = RequestMethod.DELETE)
    public String deleteSong(
            @PathVariable(value = "id") long id
    ){
        songService.deleteSong(id);
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/songs/{id}", method = RequestMethod.GET)
    public String showSong(
            @PathVariable(value = "id") long id,
            Model model
    ){
        model.addAttribute("song",songService.findSong(id));
        return "show.jsp";
    }

    @RequestMapping(value = "/songs/new",method = RequestMethod.GET)
    public String newSong(
            @ModelAttribute("song") Song song
    ){
        return "new.jsp";
    }

    @RequestMapping(value = "/songs/create" , method = RequestMethod.POST)
    public String createSong(
            @Valid @ModelAttribute("song") Song song,
            BindingResult result,
            RedirectAttributes attr
    ){
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/songs/new";
        }else{
            songService.createSong(song);
            attr.addFlashAttribute("success","Your Song has been added successfully");
            return "redirect:/dashboard";
        }
    }

    @RequestMapping(value = "search/topTen" , method = RequestMethod.GET)
    public String showTopTen(
            Model model
    ){
        model.addAttribute("songs", songService.topTenSongs());
        return "top_songs.jsp";
    }


}
