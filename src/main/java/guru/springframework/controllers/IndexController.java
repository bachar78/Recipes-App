package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping({"", "/","/index"})
    public String getIndexPage(){
        System.out.println("Some message to say.... 1234666444444");
        return "index";
    }
}