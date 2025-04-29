package com.payment.expensecontrolsystem.repositories;
import com.payment.expensecontrolsystem.data.dto.insights.TopProductsDTO;
import com.payment.expensecontrolsystem.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.payment.expensecontrolsystem.data.dto.insights.TopProductsDTO(p.name,sum(p.quantity),p.code) FROM Product p " +
            "WHERE p.createdAt >= ?1 and p.createdAt <= ?2 and p.measure = :measure " +
            "GROUP BY p.code, p.name " +
            "ORDER BY sum(p.quantity) DESC "
    )
    List<TopProductsDTO> findAllInInterval(Date startDate, Date endDate, Pageable qtdProducts, String measure);
}
