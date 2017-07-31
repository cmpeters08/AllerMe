package com.cmpeters08.lc101final.controllers;

import com.cmpeters08.lc101final.models.CompareIngredients;
import com.cmpeters08.lc101final.models.Trigger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by cmp on 7/25/2017.
 */

@Controller
@RequestMapping(value ="compare")
public class TriggerController {



    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("title", "AllergyApp");
        model.addAttribute("description", "Compare two products to find the common ingredients");

        return "compare/index";

    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String index2(Model model){
        model.addAttribute("title", "AllergyApp");
        model.addAttribute("description","Compare two products to find the common ingredients" );
        model.addAttribute("productOne", CompareIngredients.getProductOne());
        model.addAttribute("productTwo", CompareIngredients.getProductTwo());
        model.addAttribute("trigger", new Trigger());

        return "compare/results";
    }

    @RequestMapping(value="results", method = RequestMethod.GET)
    public String compareMain(Model model){
        model.addAttribute("title", "Results");
        model.addAttribute("trigger", new Trigger());
        model.addAttribute("triggerResults", Trigger.getTriggerResults());
        return "compare/results";
    }

    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String results(Model model,
                          @RequestParam String productOne, @RequestParam String productTwo){

        model.addAttribute("title", "Possible Triggers");
        model.addAttribute("productOne", productOne);
        model.addAttribute("productTwo", productTwo);

        CompareIngredients.setProductOne(productOne);
        CompareIngredients.setProductTwo(productTwo);
        model.addAttribute("compareItems", CompareIngredients.commonItems());
        return "compare/results";
    }


}
