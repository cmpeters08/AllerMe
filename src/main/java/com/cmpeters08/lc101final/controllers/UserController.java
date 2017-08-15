package com.cmpeters08.lc101final.controllers;

import com.cmpeters08.lc101final.models.CompareIngredients;
import com.cmpeters08.lc101final.models.Trigger;
import com.cmpeters08.lc101final.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by cmp on 7/25/2017.
 */

@Controller
@RequestMapping(value="user")
public class UserController extends AbstractController {

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {

        User currentUser = getUserFromSession(request.getSession());

        model.addAttribute("user", currentUser);
        model.addAttribute("title", "Saved Triggers");
        model.addAttribute("triggers", triggerDao.findByUser(currentUser));
        // model.addAttribute("triggers", triggerDao.findAll());
        return "user/index";
    }

    @RequestMapping(value = "savedresults", method = RequestMethod.GET)
    public String saveResults(Model model, @RequestParam String aTrigger) {

        model.addAttribute("title", "Saved Results");
        model.addAttribute(new Trigger());
        model.addAttribute("aTrigger", aTrigger);

        return "user/savedresults";
    }

    @RequestMapping(value = "savedresults", method = RequestMethod.POST)
    public String savedResults(@ModelAttribute @Valid Trigger newTrigger, User aUser, Model model,
                               @RequestParam String aTrigger) {

        model.addAttribute("title", "Saved Results");
        model.addAttribute("aTrigger", aTrigger);

        aUser.getUid();

        newTrigger.setUser(aUser);
        String[] manyTriggers = aTrigger.split(",");


        for (String item : manyTriggers) {
                Trigger dasTrigger = new Trigger();
                dasTrigger.setUser(aUser);
                dasTrigger.setKnownTriggers(item);
                triggerDao.save(dasTrigger);
            }

        return "user/savedresults";

    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeTrigger(Model model, HttpServletRequest request) {

        User currentUser = getUserFromSession(request.getSession());

        model.addAttribute("triggers", triggerDao.findByUser(currentUser));
        model.addAttribute("title", "Delete Triggers From the Database");

        return "user/remove";

    }

    //Allows User to Remove saved triggers from the database
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String removeTheTrigger(@RequestParam int[] triggerIds) {

        for (int triggerId : triggerIds) {
            triggerDao.delete(triggerId);
        }
        return "redirect:";
    }


    @RequestMapping(value ="add-new", method = RequestMethod.GET)
    public String addNewTrigger(Model model, HttpServletRequest request){

        User currentUser = getUserFromSession(request.getSession());
        model.addAttribute("triggers", triggerDao.findByUser(currentUser));
        model.addAttribute("user", currentUser);

        return"user/add";
    }

    @RequestMapping(value = "add-new", method = RequestMethod.POST)
    public String addNewTriggerPost(@RequestParam String newTrigger, HttpServletRequest request, Model model){

        User currentUser = getUserFromSession(request.getSession());
        ArrayList<Trigger> savedUserTriggers = triggerDao.findByUser(currentUser);
        ArrayList<String> triggerAsString = new ArrayList<>();
        for(Trigger trigger : savedUserTriggers){
            triggerAsString.add(trigger.getKnownTriggers());
        }

        String[] addManyTriggers = newTrigger.split(", ");
        ArrayList<String> compareItemDb = new ArrayList();

        for(String item : addManyTriggers){
            if(!triggerAsString.contains(item)){
                Trigger addTrigger = new Trigger();
                addTrigger.setKnownTriggers(item);
                addTrigger.setUser(currentUser);
                triggerDao.save(addTrigger);
            }
            else{
                compareItemDb.add(item);
            }
        }
        model.addAttribute("existError", compareItemDb);

        //return"user/index";
        return "user/add";
    }


    @RequestMapping(value = "compare-new", method = RequestMethod.GET)
    public String compareNewProductGet(Model model, HttpServletRequest request) {

        User currentUser = getUserFromSession(request.getSession());
        triggerDao.findByUser(currentUser);
        model.addAttribute("productOne", CompareIngredients.getProductOne());
        model.addAttribute("trigger", new Trigger());

        return "user/comparenew";
    }

    @RequestMapping(value="compare-new", method = RequestMethod.POST)
    public String compareNewProductPost(@RequestParam String productOne, Model model, HttpServletRequest request) {
        model.addAttribute("title", "these items are in your database");

        User currentUser = getUserFromSession(request.getSession());

        ArrayList<Trigger> currentUserTriggers = triggerDao.findByUser(currentUser);
        ArrayList<String> triggerStr = new ArrayList<>();


        for(Trigger item : currentUserTriggers){
            triggerStr.add(item.getKnownTriggers().toLowerCase());
        }
        CompareIngredients.setProductOne(productOne);
        CompareIngredients.setTheSavedIngredients(triggerStr);
        ArrayList commonItems = CompareIngredients.compareNewItem();

        model.addAttribute("existError", commonItems);


            //return "user/comparenewresults";
        return "user/savedresults";
    }
}