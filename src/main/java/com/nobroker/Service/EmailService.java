package com.nobroker.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.nobroker.Service.EmailVerificationService.emailOtpMapping;


@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    private final UserService userService;

    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    /* create Method --> for Generating  the OTP having 6 digits*/
    public String generateOTP(){
        Random random = new Random();
        String format = String.format("%06d", random.nextInt(1000000));
        return format;
    }

    public void sendEmail(String to, String subject, String text){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ravi@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    public Map<String, String> sendOTPEmail(String email){

        String otp = generateOTP();

        /* before sending an OTP we have to store it temporary ----> ( emailOtpMapping )*/
        String storedOtp = emailOtpMapping.put(email, otp);


        sendEmail(email,"OTP For Email Verification","Your OTP is : "+otp);

        Map<String, String> response = new HashMap<>();

        response.put("status","success");
        response.put("message","OTP send successfully");

        return response;

    }
}
