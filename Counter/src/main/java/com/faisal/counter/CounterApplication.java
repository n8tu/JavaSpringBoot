package com.faisal.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@Controller
public class CounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounterApplication.class, args);
    }

    @RequestMapping("/")
    public String index(HttpSession session){
        if(session.getAttribute("counter") == null){
            session.setAttribute("counter",1);
        }else{
            session.setAttribute("counter",(int) session.getAttribute("counter")+1);
        }

        return "index.jsp";
    }

    @RequestMapping("/counter")
    public String counter(Model model,HttpSession session){
        model.addAttribute("counter", (int) session.getAttribute("counter"));
        return "counter.jsp";
    }

}
