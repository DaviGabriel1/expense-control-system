package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.data.dto.users.UserResponseDTO;
import com.payment.expensecontrolsystem.exceptions.ResourceNotFoundException;
import com.payment.expensecontrolsystem.interfaces.IUserService;
import com.payment.expensecontrolsystem.mapper.UserMapper;
import com.payment.expensecontrolsystem.models.User;
import com.payment.expensecontrolsystem.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDTO getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("usuário não encontrado"));
        return UserMapper.toUserResponseDTO(user);
    }
}
