package ru.krivonogov.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name1, @RequestParam(value = "surname", required = false) String surname1, Model model) {

//        System.out.println("Hi, " + name1 + " " + surname1);
        model.addAttribute("message", "Hi, " + name1 + " " + surname1);

        return "first/hello";
    }


    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam(value = "a", required = false) String a1,
                                 @RequestParam(value = "b", required = false) String b1,
                                 @RequestParam(value = "action", required = false) String action1, Model model) {

        System.out.println("a1" + a1);
        System.out.println("b1" + b1);
        System.out.println("action1" + action1);

        String result ="";

        switch (action1) {
            case ("multiplication"):
                result = String.valueOf(Integer.parseInt(a1) * Integer.parseInt(b1));
                break;
            case ("addition"):
                result = String.valueOf(Integer.parseInt(a1) + Integer.parseInt(b1));
                break;
            case ("subtraction"):
                result = String.valueOf(Integer.parseInt(a1) - Integer.parseInt(b1));
                break;
            case ("division"):
                result = String.valueOf(Integer.parseInt(a1) / Integer.parseInt(b1));
                break;
        }

        model.addAttribute("operation_result", result);

        return "first/calculator";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

}
