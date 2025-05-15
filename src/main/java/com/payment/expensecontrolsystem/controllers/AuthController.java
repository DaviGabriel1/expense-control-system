package com.payment.expensecontrolsystem.controllers;

import com.payment.expensecontrolsystem.data.dto.auth.RequestLoginDTO;
import com.payment.expensecontrolsystem.data.dto.auth.RequestRegisterDTO;
import com.payment.expensecontrolsystem.data.dto.auth.ResponseLoginDTO;
import com.payment.expensecontrolsystem.data.dto.auth.ResponseRegisterDTO;
import com.payment.expensecontrolsystem.interfaces.IAuthService;
import com.payment.expensecontrolsystem.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final IAuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(IAuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody @Valid RequestLoginDTO loginDTO) {
        var emailPassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = this.authService.generateToken((User) auth.getPrincipal());
        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO(token);
        return ResponseEntity.ok(responseLoginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisterDTO> register(@RequestBody @Valid RequestRegisterDTO registerDTO) {
        if(this.authService.loadUserByUsername(registerDTO.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        ResponseRegisterDTO responseDTO = this.authService.register(registerDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
