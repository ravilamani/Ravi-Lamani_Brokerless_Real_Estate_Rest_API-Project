package com.nobroker.Service;

import com.nobroker.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmailVerificationService {

  static final Map<String,String> emailOtpMapping=new HashMap<>();

  @Autowired
  private UserService userService;
  @Autowired
  private EmailService emailService;

  HashMap<String, String> response = new HashMap<>();

  public Map<String,String> verifyOTP(String email, String otp){

      /* otp is stored before sending an email */
      String storedOtp = emailOtpMapping.get(email);

      if(storedOtp != null && storedOtp.equals(otp) ){

          User user=userService.getUserEmail(email);

          if (user != null) {
              userService.verifyEmail(user);
              /* before email verification we have to remove the otp */
              emailOtpMapping.remove(email);

              response.put("status","success");
              response.put("message","Email verified successfully");
           }
          else {
              response.put("status","error");
              response.put("message","User not found with email");
          }
       }
      else {
          response.put("status","error");
          response.put("message","OTP is Invalid");
      }
      return response;
  }


    /*-------------For User Login and Email verification-------------------------*/
    public Map<String, String> sendOtpForLogin(String email) {

       if(userService.isEmailVerified(email)){

           String otp = emailService.generateOTP();
           /* store it temporary.......*/
           emailOtpMapping.put(email,otp);
           /* send OTP to users Email ...*/
           emailService.sendOTPEmail(email);

           response.put("status","success");
           response.put("message","OTP sent successfully");
       }
       else{
           response.put("status","error");
           response.put("message","Email is not verified.....");
       }

        return response;
    }
    /*--------------OTP Verification for Login----------------------------*/
    public Map<String, String> verifyOtpForLogin(String email, String otp) {

        String storedOtp = emailOtpMapping.get(email);

        if(storedOtp!=null && storedOtp.equals(otp)){
            response.put("status","success");
            response.put("message","OTP verified successfully...");
        }

        else{
            response.put("status","error");
            response.put("message","OTP is Invalid");
        }
        return response;
    }

}
