package com.faisal.dojoandninjas.controllers;

import com.faisal.dojoandninjas.models.Dojo;
import com.faisal.dojoandninjas.services.DojoService;
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
public class DojoController {

    private final DojoService dojoService;

    public DojoController(DojoService dojoService) {
        this.dojoService = dojoService;
    }


    @RequestMapping(value = "/dojos/new",method = RequestMethod.GET)
    public String newDojo(
            @ModelAttribute("dojo") Dojo dojo
            ){
        return "new_dojo.jsp";
    }

    @RequestMapping(value = "/dojos/new",method = RequestMethod.POST)
    public String createDojo(
            @Valid @ModelAttribute("dojo") Dojo dojo,
            BindingResult result,
            RedirectAttributes attr
    ){
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/dojos/new";
        }else{
            dojoService.createDojo(dojo);
            attr.addFlashAttribute("success","Your Dojo has been added successfully");
            return "redirect:/dojos/new";
        }
    }

    @RequestMapping("/dojos/{id}")
    public String showDojo(
            @PathVariable(value = "id") long id,
            Model model
    ){
        model.addAttribute("dojo",dojoService.findDojo(id));
        return "show_dojo.jsp";
    }


}
