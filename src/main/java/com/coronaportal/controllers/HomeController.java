package com.coronaportal.controllers;

import com.coronaportal.models.Person;
import com.coronaportal.repositories.IPersonRepo;
import com.coronaportal.services.IPersonService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    IPersonService personService;

    @GetMapping("/")
    public String home(Principal principal, HttpServletResponse response){
        if(principal == null){
            return "home/index";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CITIZEN"))) {
            Person person = personService.fetchPersonData(principal.getName());
            Cookie cookie1 = new Cookie("firstName", person.getFirst_name());
            Cookie cookie2 = new Cookie("lastName", person.getLast_name());
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            return "user/index";
        }
        else if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SECRETARY"))){
            return "secretary/index";
        }
        else
            return "admin/index";
    }
}
