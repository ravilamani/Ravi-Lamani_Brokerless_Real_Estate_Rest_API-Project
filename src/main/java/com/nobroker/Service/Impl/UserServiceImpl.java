package com.nobroker.Service.Impl;

import com.nobroker.Entity.User;
import com.nobroker.Payload.UserDto;
import com.nobroker.Repository.UserRepository;
import com.nobroker.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto registerUser(UserDto userDto ) {
        User user = mapToEntity(userDto);
        User savedUsers = userRepository.save(user);

        UserDto userDtoS = mapToDto(savedUsers);

        return userDtoS;
    }

    @Override
    public User getUserEmail(String email) {
       return userRepository.findByEmail(email);

    }

    @Override
    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepository.save(user);
    }

    @Override
    public boolean isEmailVerified(String email) {
        User userEmail = userRepository.findByEmail(email);
        return userEmail != null && userEmail.isEmailVerified();
    }


    UserDto mapToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    User mapToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }



/*    @Override
    public long createUser(UserDto userDto) {
        User user = mapToEntity(userDto);
        User savedUser = userRepository.save(user);

        long id = savedUser.getId();
        return id;
    }
    }*/
}
