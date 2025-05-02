package com.payment.expensecontrolsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecretsConfig {
    @Value("${app.secrets.full-path}")
    private String fullPath; //path para armazenar arquivos

    public String getFullPath() {
        return fullPath;
    }
}
