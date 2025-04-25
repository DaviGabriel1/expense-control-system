package com.payment.expensecontrolsystem.controllers;

import com.payment.expensecontrolsystem.data.dto.users.UserResponseDTO;
import com.payment.expensecontrolsystem.interfaces.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public UserResponseDTO getUserById(@RequestParam(name = "id") Long id) throws Exception {
        return this.userService.getUserById(id);
    }
}
