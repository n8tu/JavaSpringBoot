package com.faisal.displaydate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import  java.util.Date;

@SpringBootApplication
@Controller
public class DisplayDateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisplayDateApplication.class, args);
    }

    @RequestMapping("/")
    public String index(){
        return "index.jsp";
    }

    @RequestMapping("/date")
    public String date(Model model){

        String date = String.format("%s, the %s of %s, %s",
                new SimpleDateFormat("EEEE").format(new Date()),
                new SimpleDateFormat("d").format(new Date()),
                new SimpleDateFormat("MMMM").format(new Date()),
                new SimpleDateFormat("yyyy").format(new Date())
                );

        model.addAttribute("date",date);
        return "date.jsp";
    }

    @RequestMapping("/time")
    public String time(Model model){
        SimpleDateFormat s1 = new SimpleDateFormat("h:m a");
        String time = s1.format(new Date());
        model.addAttribute("time",time);
        return "time.jsp";
    }

}
