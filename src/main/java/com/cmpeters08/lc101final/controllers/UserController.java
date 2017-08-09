package com.cmpeters08.lc101final.controllers;

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

/**
 * Created by cmp on 7/25/2017.
 */

@Controller
@RequestMapping(value="user")
public class UserController extends AbstractController{

@RequestMapping(value="")
public String index(Model model, HttpServletRequest request){


    User currentUser = getUserFromSession(request.getSession());

   model.addAttribute("user", currentUser);
    model.addAttribute("title", "Saved Triggers");
    model.addAttribute("triggers", triggerDao.findByUser(currentUser));
   // model.addAttribute("triggers", triggerDao.findAll());
    return "user/index";
}

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

    return "user/savedresults";

}

@RequestMapping(value="remove", method=RequestMethod.GET)
public String removeTrigger(Model model, HttpServletRequest request){

    User currentUser = getUserFromSession(request.getSession());

    model.addAttribute("triggers", triggerDao.findByUser(currentUser));
    model.addAttribute("title", "Delete Triggers From the Database");

    return "user/remove";

}

@RequestMapping(value="remove", method = RequestMethod.POST)
    public String removeTheTrigger(@RequestParam int[] triggerIds){

    for(int triggerId : triggerIds){
        triggerDao.delete(triggerId);
    }
    return"redirect:";
}


}
