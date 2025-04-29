package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.data.dto.insights.TopProductsDTO;

import java.util.Date;
import java.util.List;

public interface IInsightServices {
    List<TopProductsDTO> getTopProducts(Date startDate, Date endDate, Integer pageable, String measure);
}
