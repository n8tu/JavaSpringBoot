package com.faisal.dojoandninjas.controllers;

import com.faisal.dojoandninjas.models.Ninja;
import com.faisal.dojoandninjas.services.DojoService;
import com.faisal.dojoandninjas.services.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class NinjaController {

    private final NinjaService ninjaService;
    private final DojoService dojoService;

    public NinjaController(NinjaService ninjaService, DojoService dojoService) {
        this.ninjaService = ninjaService;
        this.dojoService = dojoService;
    }

    @RequestMapping(value = "/ninjas/new",method = RequestMethod.GET)
    public String newNinja(
            Model model,
            @ModelAttribute("ninja") Ninja ninja
            ){
        model.addAttribute("dojos",dojoService.allDojos());
        return "new_ninja.jsp";
    }

    @RequestMapping(value = "/ninjas/new",method = RequestMethod.POST)
    public String createNinja(
            @Valid @ModelAttribute("ninja") Ninja ninja,
            BindingResult result,
            RedirectAttributes attr
    ){
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/ninjas/new";
        }else{
            ninjaService.createNinja(ninja);
            attr.addFlashAttribute("success","Ninja has been added successfully");
            return "redirect:/ninjas/new";
        }
    }
}
