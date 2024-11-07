package com.suhas.flight_booking.controller;

import com.suhas.flight_booking.dtos.UserRequestDto;
import com.suhas.flight_booking.dtos.UserResponseDto;
import com.suhas.flight_booking.models.User;
import com.suhas.flight_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user){
        User user1 = userService.registerUser(user.getUserName(),user.getEmail(),user.getPwd());
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user1.getId());
        responseDto.setName(user1.getName());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
