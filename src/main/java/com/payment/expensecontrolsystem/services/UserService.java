package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.data.dto.users.UserResponseDTO;
import com.payment.expensecontrolsystem.exceptions.ResourceNotFoundException;
import com.payment.expensecontrolsystem.interfaces.IUserService;
import com.payment.expensecontrolsystem.mapper.UserMapper;
import com.payment.expensecontrolsystem.models.User;
import com.payment.expensecontrolsystem.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDTO getUserById(Long id) {
        logger.info("buscando usuário de id {}",id);
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("usuário não encontrado"));
        return UserMapper.toUserResponseDTO(user);
    }

    @Override
    public UserDetails getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}
