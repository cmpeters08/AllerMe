package com.cmpeters08.lc101final.controllers;

import com.cmpeters08.lc101final.models.Trigger;
import com.cmpeters08.lc101final.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by cmp on 7/25/2017.
 */

@Controller
@RequestMapping(value="user")
public class UserController extends AbstractController{

@RequestMapping(value = "savedresults", method = RequestMethod.GET)
public String saveResults(Model model,  @RequestParam String aTrigger){

    model.addAttribute("title", "Saved Results");
    model.addAttribute(new Trigger());
    model.addAttribute("aTrigger", aTrigger);

    return "user/savedresults";
}

@RequestMapping(value="savedresults", method = RequestMethod.POST)
public String savedResults(@ModelAttribute @Valid Trigger newTrigger, User aUser, Model model, @RequestParam String aTrigger){

    model.addAttribute("title", "Saved Results");
    model.addAttribute("aTrigger", aTrigger);

    aUser.getUid();

    newTrigger.setUser(aUser);
    String[] manyTriggers = aTrigger.split(",");

    for(String item : manyTriggers) {
        Trigger dasTrigger = new Trigger();
        dasTrigger.setUser(aUser);
        dasTrigger.setKnownTriggers(item);
        triggerDao.save(dasTrigger);
    }


//    newTrigger.setKnownTriggers(aTrigger);
//    newTrigger.setUser(aUser);
//    triggerDao.save(newTrigger);

    return "user/savedresults";

    //return "redirect:";

}



}
