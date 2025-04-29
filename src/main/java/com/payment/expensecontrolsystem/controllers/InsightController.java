package com.payment.expensecontrolsystem.controllers;

import com.payment.expensecontrolsystem.data.dto.insights.TopProductsDTO;
import com.payment.expensecontrolsystem.interfaces.IInsightServices;
import com.payment.expensecontrolsystem.models.Product;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/insights")
public class InsightController {
    private final IInsightServices insightServices;

    public InsightController(IInsightServices insightServices){
        this.insightServices = insightServices;
    }

    @GetMapping("/top-products")
    public List<TopProductsDTO> getInsights(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(name = "qtdProducts") Integer qtdProducts,
            @RequestParam(name = "measure", required = false) String measure
    ){
        return this.insightServices.getTopProducts(startDate, endDate, qtdProducts, measure);
    }
}
