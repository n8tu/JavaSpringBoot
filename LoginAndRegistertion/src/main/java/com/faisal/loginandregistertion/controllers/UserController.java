package com.faisal.loginandregistertion.controllers;

import com.faisal.loginandregistertion.models.Login;
import com.faisal.loginandregistertion.models.User;
import com.faisal.loginandregistertion.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(){
        return "redirect:/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(
            @ModelAttribute("login") Login login
    ){
        return "login.jsp";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String processLogin(
            @Valid @ModelAttribute("login") Login login,
            BindingResult result,
            RedirectAttributes attr,
            HttpSession session
    ){
         User user = userService.login(login,result);
         if(result.hasErrors()){
             attr.addFlashAttribute("errors",result.getFieldErrors());
             return "redirect:/login";
         }else{
             session.setAttribute("user_id",user.getId());
             return "redirect:/success";
         }
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(
            @ModelAttribute("register") User user
    ){
        return "register.jsp";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegister(
            @Valid @ModelAttribute("register") User u,
            BindingResult result,
            RedirectAttributes attr,
            HttpSession session
    ){
        User user = userService.register(u,result);
        if(result.hasErrors()){
            attr.addFlashAttribute("errors",result.getFieldErrors());
            return "redirect:/register";
        }else{
            session.setAttribute("user_id",user.getId());
            return "redirect:/success";
        }
    }

    @RequestMapping("/success")
    public String success(
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("user_id") == null){
            return "redirect:/login";
        }
        long user_id = (long) session.getAttribute("user_id");
        User user = userService.findUser(user_id);
        model.addAttribute("user",user);
        return "success.jsp";
    }

    @RequestMapping("/logout")
    public String logout(
            HttpSession session
    ){
        session.removeAttribute("user_id");
        return "redirect:/";
    }






}
