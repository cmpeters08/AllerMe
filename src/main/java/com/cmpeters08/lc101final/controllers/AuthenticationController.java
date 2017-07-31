package com.cmpeters08.lc101final.controllers;


import com.cmpeters08.lc101final.models.User;
import com.cmpeters08.lc101final.models.forms.LoginForm;
import com.cmpeters08.lc101final.models.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * Created by LaunchCode
 */
@Controller
public class AuthenticationController extends AbstractController {

    @RequestMapping(value = "")
    public String index() {
        return "signup/index";
    }

    @RequestMapping(value = "/register")
    public String registerForm(Model model) {
        model.addAttribute(new RegisterForm());
        model.addAttribute("title", "Register");
        return "signup/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "signup/register";
        }

        User existingUser = userDao.findByUsername(form.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return "signup/register";
        }

        User newUser = new User(form.getUsername(), form.getPassword());
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute(new LoginForm());
        model.addAttribute("title", "Log In");
        return "signup/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid LoginForm form, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "signup/login";
        }

        User theUser = userDao.findByUsername(form.getUsername());
        String password = form.getPassword();

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            return "signup/login";
        }

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "signup/login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
