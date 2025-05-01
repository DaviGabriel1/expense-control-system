package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.interfaces.ISendEmailService;
import org.springframework.stereotype.Service;

@Service
public class SesService implements ISendEmailService {

    @Override
    public void sendEmail(String to, String subject, String body) {

    }
}
