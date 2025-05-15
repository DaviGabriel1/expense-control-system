package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.data.dto.auth.RequestLoginDTO;
import com.payment.expensecontrolsystem.data.dto.auth.RequestRegisterDTO;
import com.payment.expensecontrolsystem.data.dto.auth.ResponseLoginDTO;
import com.payment.expensecontrolsystem.data.dto.auth.ResponseRegisterDTO;
import com.payment.expensecontrolsystem.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IAuthService {
    ResponseRegisterDTO register(RequestRegisterDTO requestRegisterDTO);
    ResponseLoginDTO login(RequestLoginDTO requestLoginDTO);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    String generateToken(User user);
}
