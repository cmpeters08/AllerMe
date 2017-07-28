package com.cmpeters08.lc101final.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cmp on 7/25/2017.
 */

@Controller
@RequestMapping(value="user")
public class UserController {

@RequestMapping(value ="")
public String index(Model model){

    model.addAttribute("title", "AllergyApp");
    model.addAttribute("description", "Sign in or Register to save your data");
    return "user/index";
}



}
