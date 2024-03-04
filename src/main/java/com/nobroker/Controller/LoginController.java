package com.nobroker.Controller;

import com.nobroker.Service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private EmailVerificationService emailVerificationService;


    /*create method ---> That it should take (email) and to that it should send OTP */
    /* http://localhost:8080/api/login/send-otp-for-login?email= */
    @PostMapping("/send-otp-for-login")
    public Map<String,String> sendOtpForLogin(@RequestParam String email){
       return emailVerificationService.sendOtpForLogin(email);
    }


    /* http://localhost:8080/api/login/verify-otp-for-login?email=&otp= */
    @PostMapping("/verify-otp-for-login")
    public Map<String,String> verifyOtpForLogin(@RequestParam String email,@RequestParam String otp){
        return emailVerificationService.verifyOtpForLogin(email,otp);

    }




}
