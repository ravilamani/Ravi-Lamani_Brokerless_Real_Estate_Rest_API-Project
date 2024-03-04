package com.nobroker.Controller;

import com.nobroker.Entity.User;
import com.nobroker.Payload.UserDto;
import com.nobroker.Repository.UserRepository;
import com.nobroker.Service.EmailService;
import com.nobroker.Service.EmailVerificationService;
import com.nobroker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    /* http://localhost:8080/api/registration/userReg  */
    private UserService userService;
    private EmailService emailService;

    private EmailVerificationService emailVerificationService;


    public RegistrationController(UserService userService, EmailService emailService, EmailVerificationService emailVerificationService) {
        this.userService = userService;
        this.emailService = emailService;
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/userReg")
    public Map<String,String> registerUser(@RequestBody UserDto userDto){

        UserDto detailOfUsers= userService.registerUser(userDto);

        /* Once we save the (detailsOfUsers) we have to send the OTP to email for email verification */
        return emailService.sendOTPEmail(userDto.getEmail());
    }


    /* Create Method ---> for verification of (email) & (otp) */
    /* http://localhost:8080/api/registration/verify-otp?email=&otp= */
    @PostMapping("/verify-otp")
    public Map<String, String> verifyOTP(@RequestParam String email, @RequestParam String otp) {
          return emailVerificationService.verifyOTP(email, otp);
    }


}
