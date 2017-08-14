package com.cmpeters08.lc101final.controllers;

import com.cmpeters08.lc101final.models.CompareIngredients;
import com.cmpeters08.lc101final.models.Trigger;
import com.cmpeters08.lc101final.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by cmp on 7/25/2017.
 */

@Controller
@RequestMapping(value ="compare")
public class TriggerController extends AbstractController{


    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("title", "AllergyApp");
        model.addAttribute("description", "Compare two products to find the common ingredients");

        //model.addAttribute("username", userDao.findByUsername(userSessionKey));
        return "compare/index";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String index2(Model model, @RequestParam String username){
        model.addAttribute("title", "AllergyApp");
        model.addAttribute("description","Compare two products to find the common ingredients" );
        model.addAttribute("productOne", CompareIngredients.getProductOne());
        model.addAttribute("productTwo", CompareIngredients.getProductTwo());
        model.addAttribute("trigger", new Trigger());
       // model.addAttribute("username", username);

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
                          @RequestParam String productOne, @RequestParam String productTwo, HttpServletRequest request){


        User currentUser = getUserFromSession(request.getSession());
       ArrayList<Trigger> savedUserTriggers = triggerDao.findByUser(currentUser);
       //convert trigger to string
        ArrayList<String> trstr = new ArrayList<>(); //this holds the database stuff as strings
        for(Trigger trigger : savedUserTriggers){
            trstr.add(trigger.getKnownTriggers());
        }
        ///

        model.addAttribute("title", "Possible Triggers");
        model.addAttribute("productOne", productOne);
        model.addAttribute("productTwo", productTwo);

        model.addAttribute("trigger", new Trigger());

        CompareIngredients.setProductOne(productOne);
        CompareIngredients.setProductTwo(productTwo);

        ArrayList<String> myCompare = CompareIngredients.commonItems();


    //add exist error to compare/results and test

        ArrayList<String> compareItemDb = new ArrayList();
        for(String item : myCompare){
            if(trstr.contains(item)){
               // myCompare.remove(item);
                compareItemDb.add(item);
            }
        }
        for(String item : compareItemDb){
            myCompare.remove(item);
        }
      //  model.addAttribute("existError", compareItemDb);

        System.out.println(myCompare);
        System.out.println(trstr);
        System.out.println(compareItemDb);
        model.addAttribute("existError", compareItemDb);
        model.addAttribute("compareItems", myCompare);



        //model.addAttribute("compareItems", CompareIngredients.commonItems());
        return "compare/results";
    }




}
