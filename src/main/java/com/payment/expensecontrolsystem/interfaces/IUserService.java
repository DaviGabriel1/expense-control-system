package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.data.dto.users.UserResponseDTO;

public interface IUserService {
    UserResponseDTO getUserById(Long id);
}
