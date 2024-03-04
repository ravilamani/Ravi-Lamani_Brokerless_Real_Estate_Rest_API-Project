package com.nobroker.Service;

import com.nobroker.Entity.User;
import com.nobroker.Payload.UserDto;

import java.util.Optional;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    User getUserEmail(String email);

    void verifyEmail(User user);

    boolean isEmailVerified(String email);


    // Once the User is Signed in I want to return a ID(Number)
    /*public long createUser(UserDto userDto);*/




}
