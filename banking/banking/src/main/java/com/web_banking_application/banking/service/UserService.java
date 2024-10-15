package com.web_banking_application.banking.service;

import java.util.List;
import com.web_banking_application.banking.dto.UsersDto;



public interface UserService {
    UsersDto createUsers(UsersDto userDto);
    UsersDto getUsersByID(Long userID);
    List<UsersDto> getAllUsers();
    UsersDto updateUser(Long userId, UsersDto updatedUser);
    void deleteUser(Long userId);
    List<UsersDto> getAllUserss();
    void registerUser(UsersDto userDto);
}