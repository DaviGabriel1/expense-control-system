package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.data.dto.insights.TopProductsDTO;
import com.payment.expensecontrolsystem.interfaces.IInsightServices;
import com.payment.expensecontrolsystem.interfaces.IInvoiceService;
import com.payment.expensecontrolsystem.interfaces.IProductService;
import com.payment.expensecontrolsystem.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InsightServices implements IInsightServices {
    private final IProductService productService;
    private final IInvoiceService invoiceService;

    public InsightServices(IProductService productService, IInvoiceService invoiceService) {
        this.productService = productService;
        this.invoiceService = invoiceService;
    }

    @Override
    public List<TopProductsDTO> getTopProducts(Date startDate, Date endDate, Integer qtdProducts, String measure) {
        if(!DateUtils.isDatesValidTime(startDate, endDate) || !DateUtils.isDateValidFormat(startDate) || !DateUtils.isDateValidFormat(endDate)) {
            throw new IllegalArgumentException("Invalid dates");
        }
        return this.productService.findAllInInterval(startDate,endDate, qtdProducts, measure);
    }
}
