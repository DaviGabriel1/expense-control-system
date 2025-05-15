package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.data.dto.auth.RequestLoginDTO;
import com.payment.expensecontrolsystem.data.dto.auth.RequestRegisterDTO;
import com.payment.expensecontrolsystem.data.dto.auth.ResponseLoginDTO;
import com.payment.expensecontrolsystem.data.dto.auth.ResponseRegisterDTO;
import com.payment.expensecontrolsystem.infrastructure.security.TokenService;
import com.payment.expensecontrolsystem.interfaces.IAuthService;
import com.payment.expensecontrolsystem.interfaces.IUserService;
import com.payment.expensecontrolsystem.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService, UserDetailsService {
    private IUserService userService;
    private TokenService tokenService;

    public AuthService(IUserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }
    @Override
    public ResponseRegisterDTO register(RequestRegisterDTO registerDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User((registerDTO.firstName() + " " + registerDTO.lastName()), registerDTO.email(), encryptedPassword, registerDTO.role());
        this.userService.createUser(newUser);
        return new ResponseRegisterDTO("usuário criado com sucesso");
    }

    @Override
    public ResponseLoginDTO login(RequestLoginDTO requestLoginDTO) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userService.getUserByEmail(email);
    }

    @Override
    public String generateToken(User user) {
        return this.tokenService.generateToken(user);
    }
}
