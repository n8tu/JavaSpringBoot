package com.faisal.ninjagold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
@Controller
public class NinjaGoldApplication {

    public static void main(String[] args) {
        SpringApplication.run(NinjaGoldApplication.class, args);
    }

    @RequestMapping("/")
    public String index(Model model , HttpSession session){
        if (session.getAttribute("gold") == null){
            session.setAttribute("gold",0);
            ArrayList<String> activites = new ArrayList<String>();
            session.setAttribute("activites",activites);
        }

        model.addAttribute("gold",(int)session.getAttribute("gold"));
        model.addAttribute("activites",(ArrayList<String>) session.getAttribute("activites"));
        return "index.jsp";
    }

    @RequestMapping("/process_money")
    public String process(@RequestParam(value="location") String loca){
        int chance = 100;
        int generateGold = 0;
        String location = loca;

        if (location.equals("quest")){
            generateGold = getRandomNumber(0,50);
            chance = 50;
        }else{
            generateGold = getRandomNumber(10,20);
        }

        if(getRandomNumber(1,100) <= chance) {
            return String.format("redirect:/increase?gold=%d&location=%s",generateGold,location);
        }else{
            return String.format("redirect:/decrease?gold=%d&location=%s",generateGold,location);
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @RequestMapping("/increase")
    public String increase(
            @RequestParam(value="gold") int gold,
            @RequestParam(value="location") String location,
            HttpSession session
            ){
        session.setAttribute("gold",(int)session.getAttribute("gold") + gold);
        ArrayList<String> activites = (ArrayList<String>) session.getAttribute("activites");
        activites.add(
                String.format("You entered a %s and earned %d gold. (%s)",location, gold, new Date().toString())
        );
        session.setAttribute("activites" ,activites);

        return "redirect:/";
    }

    @RequestMapping("/decrease")
    public String decrease(
            @RequestParam(value="gold") int gold,
            @RequestParam(value="location") String location,
            HttpSession session
    ){
        session.setAttribute("gold",(int)session.getAttribute("gold") - gold);
        ArrayList<String> activites = (ArrayList<String>) session.getAttribute("activites");
        activites.add(
                String.format("You failed a %s and lost %d gold. Ouch. (%s)",location, gold, new Date().toString())
        );
        session.setAttribute("activites" ,activites);

        return "redirect:/";
    }



    @RequestMapping("/destroy")
    public String destroy(HttpSession session){
        if(session.getAttribute("gold") != null){
            session.setAttribute("gold",null);
            session.setAttribute("activities",null);
        }

        return "redirect:/";
    }


}
