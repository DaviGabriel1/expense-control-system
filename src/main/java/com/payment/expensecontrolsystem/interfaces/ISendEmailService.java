package com.payment.expensecontrolsystem.interfaces;

public interface ISendEmailService {
    void sendEmail(String to, String subject, String body);
}
