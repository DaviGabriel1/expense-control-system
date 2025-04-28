package com.payment.expensecontrolsystem.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IOCRService {
    String turnImageToString(MultipartFile file) throws Exception;
}
