package com.web_banking_application.banking.Mapper;

import com.web_banking_application.banking.dto.UsersDto;
import com.web_banking_application.banking.entities.users;

public class UsersMapper {

    // Convert users Entity to UsersDto
    public static UsersDto mapToUsersDto(users user) {
        return new UsersDto(
                user.getUserId(),
                user.getFirst_Name(),
                user.getLast_Name(),
                user.getEmail(),
                user.getPassword(),
                user.getMobile()
        );
    }

    // Convert UsersDto to users Entity
    public static users mapToUsers(UsersDto usersDto) {
        return new users(
                usersDto.getId(),
                usersDto.getFirst_Name(),
                usersDto.getLast_Name(),
                usersDto.getEmail(),
                usersDto.getPassword(),
                usersDto.getMobile()
        );
    }

    // Convert UsersDto to users Entity (Alternative method name)
    public static users toEntity(UsersDto usersDto) {
        users user = new users();
        user.setUserId(usersDto.getId());
        user.setFirst_Name(usersDto.getFirst_Name());
        user.setLast_Name(usersDto.getLast_Name());
        user.setEmail(usersDto.getEmail());
        user.setPassword(usersDto.getPassword()); // Password will be encoded before saving
        user.setMobile(usersDto.getMobile());
        return user;
    }

    // Convert users Entity to UsersDto (Alternative method name)
    public static UsersDto toDto(users user) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(user.getUserId());
        usersDto.setFirst_Name(user.getFirst_Name());
        usersDto.setLast_Name(user.getLast_Name());
        usersDto.setEmail(user.getEmail());
        usersDto.setPassword(user.getPassword()); // Password will be encoded
        usersDto.setMobile(user.getMobile());
        return usersDto;
    }
}
