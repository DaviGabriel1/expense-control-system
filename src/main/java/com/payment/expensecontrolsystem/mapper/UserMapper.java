package com.payment.expensecontrolsystem.mapper;

import com.payment.expensecontrolsystem.data.dto.users.UserResponseDTO;
import com.payment.expensecontrolsystem.models.User;

public class UserMapper {

    public static UserResponseDTO toUserResponseDTO(User user){
        return new UserResponseDTO.Builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
