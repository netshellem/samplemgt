package com.design.samplemgt.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.design.samplemgt.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Null;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String loginPage(Model model) {
        //model.addAttribute("title", "Welcome");
        //model.addAttribute("message", "This is welcome page!");
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }
    @RequestMapping(value = {"/artist"}, method = RequestMethod.GET)
    public String artistPage(Model model, @PathVariable(value="year",required = false) String year){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy");
        String current = ft.format(dNow);
        int y = Integer.parseInt(current);
        if(year == null)
            year = current;
        model.addAttribute("ActiveYear", year);
        List<String> naviList=new ArrayList<>();

        while(y > 2015 ){
            naviList.add(String.valueOf(y));
            y--;
        }
        model.addAttribute("navilist", naviList);
        return "artist";
    }
    @RequestMapping(value = {"/admin/user"}, method = RequestMethod.GET)
    public String userPage(Model model, @PathVariable(value="year",required = false) String year){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy");
        String current = ft.format(dNow);
        int y = Integer.parseInt(current);
        if(year == null)
            year = current;
        model.addAttribute("ActiveYear", year);
        List<String> naviList=new ArrayList<>();

        while(y > 2015 ){
            naviList.add(String.valueOf(y));
            y--;
        }
        model.addAttribute("navilist", naviList);
        return "user";
    }

    @RequestMapping(value = {"/home/{year}"}, method = RequestMethod.GET)
    public String homePage(Model model, @PathVariable(value="year",required = false) String year) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy");
        String current = ft.format(dNow);
        int y = Integer.parseInt(current);
        if(year == null)
            year = current;
        model.addAttribute("ActiveYear", year);
        List<String> naviList=new ArrayList<>();

        while(y > 2015 ){
            naviList.add(String.valueOf(y));
            y--;
        }
        model.addAttribute("navilist", naviList);
        return "home";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage(Model model) {
//
//        return "loginPage";
//    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "login";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

}