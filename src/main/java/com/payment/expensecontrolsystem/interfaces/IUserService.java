package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.data.dto.users.UserResponseDTO;
import com.payment.expensecontrolsystem.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    UserResponseDTO getUserById(Long id);
    UserDetails getUserByEmail(String email);
    User createUser(User user);
}
