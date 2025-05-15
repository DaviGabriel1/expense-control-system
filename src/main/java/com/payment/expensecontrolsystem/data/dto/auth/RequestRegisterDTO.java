package com.payment.expensecontrolsystem.data.dto.auth;

import com.payment.expensecontrolsystem.enums.Roles;

public record RequestRegisterDTO(String email, String password, String firstName, String lastName, Roles role) {
}
