package com.simplilearn.simplilearn.controller;

import com.simplilearn.simplilearn.entity.User;
import com.simplilearn.simplilearn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("login")
    public Map<String, String> login(@RequestParam("username") String username, @RequestParam("password") String password){
        Map<String, String> result = new HashMap<>();
        User user = userRepository.findByUserName(username);
        if (user == null || !user.getPassWord().equals(password)) {
            result.put("login", "false");
        }else{
            result.put("login", "true");
        }
        return  result;
    }


    @PostMapping("register")
    public Map<String, String> register(@ModelAttribute("user") @Valid User user) {
        Map<String, String> result = new HashMap<>();
        userRepository.save(user);
        result.put("success", "true");
        return  result;
    }
}
