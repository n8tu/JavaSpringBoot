package com.faisal.addtopokebook.controllers;

import com.faisal.addtopokebook.models.Poke;
import com.faisal.addtopokebook.services.PokeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PokeController {
    private final PokeService pokeService;

    public PokeController(PokeService pokeService) {
        this.pokeService = pokeService;
    }

    @RequestMapping("/")
    public String index(){
        return "redirect:/expenses";
    }
    @RequestMapping("/expenses")
    public String expenses(
            Model model,
            @ModelAttribute("poke") Poke poke){
        model.addAttribute("pokes",pokeService.getAllPokes());
        return "index.jsp";
    }

    @RequestMapping(value = "/expenses/create", method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("poke") Poke poke ,
            BindingResult result,
            RedirectAttributes attr
    )
    {
        if(result.hasErrors()){
            attr.addFlashAttribute("errors", result.getFieldErrors());
            return "redirect:/expenses";
        }else{
            pokeService.createPoke(poke);
            attr.addFlashAttribute("success", "Poke has been added successfully!");
            return "redirect:/expenses";
        }
    }

    @RequestMapping("/expenses/edit/{id}")
    public String edit(
            @PathVariable(value = "id") long id,
            Model model
    ){
        model.addAttribute("poke",pokeService.getPoke(id));
        return "edit.jsp";
    }

    @RequestMapping(value = "/expenses/update/{id}",method = RequestMethod.PUT)
    public String update(
            @PathVariable(value = "id") long id,
            @Valid @ModelAttribute("poke") Poke poke,
            BindingResult result,
            RedirectAttributes attr

    ){
        if(result.hasErrors()){
            attr.addFlashAttribute("errors", result.getFieldErrors());
            return "redirect:/expenses/edit/"+id;
        }else{
            pokeService.updatePoke(id,poke);
            attr.addFlashAttribute("success", "Poke has been edited successfully!");
            return "redirect:/expenses";
        }
    }

    @RequestMapping("/expenses/{id}")
    public String show(
            @PathVariable(value = "id") long id,
            Model model
    ){
        model.addAttribute("poke",pokeService.getPoke(id));
        return "show.jsp";
    }

    @RequestMapping(value= "/expenses/delete/{id}", method = RequestMethod.DELETE)
    public String delete(
            @PathVariable(value = "id") long id
    ){
        pokeService.deletePoke(id);
        return "redirect:/expenses";
    }




}
