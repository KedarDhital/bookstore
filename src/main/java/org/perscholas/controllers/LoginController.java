package org.perscholas.controllers;

import org.perscholas.dao.IRoleRepo;
import org.perscholas.dao.IUserRepo;
import org.perscholas.global.GlobalData;
import org.perscholas.model.Role;
import org.perscholas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IRoleRepo roleRepo;

    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";

 }
 @GetMapping("/register")
    public String getRegister(){
        return "register";
 }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepo.findById(2).get());
        user.setRoles(roles);
        userRepo.save(user);
        request.login(user.getEmail(),password);
        return "redirect:/";
    }
}
