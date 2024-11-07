package com.suhas.flight_booking.service;

import com.suhas.flight_booking.models.User;
import com.suhas.flight_booking.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String name,String email, String pwd){
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPwd(pwd);
        return userRepository.save(user);
    }
}
