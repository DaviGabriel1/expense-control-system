package com.payment.expensecontrolsystem;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseControlSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseControlSystemApplication.class, args);
    }

    @Bean
    public Tesseract tesseract() {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("por");
        tesseract.setDatapath("C:\\Users\\davi.santos\\Documents\\tesseract-tests");
        return tesseract;
    }

}
