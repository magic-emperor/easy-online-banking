package com.web_banking_application.banking.service.implimentation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web_banking_application.banking.Mapper.UsersMapper;
import com.web_banking_application.banking.dto.UsersDto;
import com.web_banking_application.banking.entities.users;
import com.web_banking_application.banking.exception.ResourceNotFoundException;
import com.web_banking_application.banking.repositories.UsersRepositories;
import com.web_banking_application.banking.service.UserService;

@Service
public class UsersServiceImplementation implements UserService {
    
    private final UsersRepositories usersRepositories;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImplementation(UsersRepositories usersRepositories, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepositories = usersRepositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsersDto createUsers(UsersDto userDto) {
        users Users = UsersMapper.mapToUsers(userDto);
        Users.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password
        users savedUsers = usersRepositories.save(Users);
        return UsersMapper.mapToUsersDto(savedUsers);
    }

    @Override
    public UsersDto getUsersByID(Long userID) {
        users Users = usersRepositories.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found:" + userID));
        return UsersMapper.mapToUsersDto(Users);
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<users> Users = usersRepositories.findAll();
        return Users.stream().map(UsersMapper::mapToUsersDto).collect(Collectors.toList());
    }

    @Override
    public UsersDto updateUser(Long userId, UsersDto updatedUser) {
        users Users = usersRepositories.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found:" + userId));
        Users.setFirst_Name(updatedUser.getFirst_Name());
        Users.setLast_Name(updatedUser.getLast_Name());
        Users.setEmail(updatedUser.getEmail());
        Users.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Encrypt password when updating
        Users.setMobile(updatedUser.getMobile());
        users Users1 = usersRepositories.save(Users);
        return UsersMapper.mapToUsersDto(Users1);
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepositories.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Does Not Exist:" + userId));
        usersRepositories.deleteById(userId);
    }

    @Override
    public List<UsersDto> getAllUserss() {
        List<users> Users = usersRepositories.findAll();
        return Users.stream()
            .map(UsersMapper::mapToUsersDto)
            .collect(Collectors.toList());
    }

    @Override
    public void registerUser(UsersDto userDto) {
        users user = new users();
        user.setFirst_Name(userDto.getFirst_Name());
        user.setLast_Name(userDto.getLast_Name());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password
        user.setMobile(userDto.getMobile());
        usersRepositories.save(user);
    }
}