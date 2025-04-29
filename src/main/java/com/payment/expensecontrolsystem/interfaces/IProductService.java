package com.payment.expensecontrolsystem.interfaces;

import com.payment.expensecontrolsystem.data.dto.insights.TopProductsDTO;
import com.payment.expensecontrolsystem.models.Product;

import java.util.Date;
import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<Product> saveAllProducts(List<Product> products);
    Product saveProduct(Product product);
    List<TopProductsDTO> findAllInInterval(Date startDate, Date endDate, Integer qtdProducts, String measure);
}
