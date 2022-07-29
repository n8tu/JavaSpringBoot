package com.faisal.languages.controllers;

import com.faisal.languages.models.Language;
import com.faisal.languages.services.LangaugeService;
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
public class LanguageController {

    private final LangaugeService langaugeService;

    public LanguageController(LangaugeService langaugeService) {
        this.langaugeService = langaugeService;
    }

    @RequestMapping("/")
    public String index(){
        return "redirect:/languages";
    }

    @RequestMapping(value = "/languages" , method = RequestMethod.GET)
    public String languages(
            Model model,
            @ModelAttribute("language") Language language
            ){
        model.addAttribute("languages",langaugeService.allLanguages());
        return "index.jsp";
    }

    @RequestMapping(value = "/languages" , method = RequestMethod.POST)
    public String createLanguage(
            @Valid @ModelAttribute("language") Language language,
            BindingResult result,
            RedirectAttributes attr
    ){
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/languages";
        }else{
            langaugeService.createLanguage(language);
            attr.addFlashAttribute("success","Language has been created successfully");
            return "redirect:/languages";
        }
    }

    @RequestMapping(value = "/languages/{id}", method = RequestMethod.GET)
    public String showLanguage(
            @PathVariable(value = "id") long id,
            Model model
    ){
        model.addAttribute("language",langaugeService.findLanguage(id));
        return "show_language.jsp";
    }

    @RequestMapping(value = "/languages/{id}/edit", method = RequestMethod.GET)
    public String editLanguage(
            @PathVariable(value = "id") long id,
            Model model
    ){
        model.addAttribute("language",langaugeService.findLanguage(id));
        return "edit_language.jsp";
    }

    @RequestMapping(value = "/languages/{id}", method = RequestMethod.PUT)
    public String updateLanguage(
            @PathVariable(value = "id") long id,
            @Valid @ModelAttribute("language") Language language,
            BindingResult result,
            RedirectAttributes attr
    ){
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/languages/"+ id + "/edit";
        }else{
            langaugeService.updateLanguage(id,language);
            attr.addFlashAttribute("success","Language has been updated successfully");
            return "redirect:/languages";
        }
    }

    @RequestMapping(value = "/languages/{id}", method = RequestMethod.DELETE)
    public String deleteLanguage(
            @PathVariable(value = "id") long id
    ){
        langaugeService.deleteLanguage(id);
        return "redirect:/languages";
    }






}
