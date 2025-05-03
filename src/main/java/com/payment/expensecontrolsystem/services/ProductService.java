package com.payment.expensecontrolsystem.services;

import com.payment.expensecontrolsystem.data.dto.insights.TopProductsDTO;
import com.payment.expensecontrolsystem.exceptions.ResourceNotFoundException;
import com.payment.expensecontrolsystem.interfaces.IProductService;
import com.payment.expensecontrolsystem.models.Product;
import com.payment.expensecontrolsystem.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("produto não encontrado"));
    }

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> saveAllProducts(List<Product> products) {
        return this.productRepository.saveAll(products);
    }

    public List<TopProductsDTO> findAllInInterval(Date startDate, Date endDate, Integer qtdProducts, String measure) {
        return this.productRepository.findAllInInterval(startDate, endDate, PageRequest.of(0, qtdProducts), measure);
    }
}
