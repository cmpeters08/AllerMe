package com.cmpeters08.lc101final.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by cmp on 7/25/2017.
 */

@Controller
@RequestMapping(value="user")
public class UserController {

@RequestMapping(value = "savedresults")
public String saveResults(Model model, @RequestParam String item){

    model.addAttribute("title", "Saved Results");

    return "user/savedresults";
}



}
