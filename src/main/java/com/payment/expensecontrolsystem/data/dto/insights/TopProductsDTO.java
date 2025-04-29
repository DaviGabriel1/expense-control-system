package com.payment.expensecontrolsystem.data.dto.insights;

public class TopProductsDTO {
    /*private final List<Product> topProducts;

    public TopProductsDTO(List<Product> topProducts) {
        this.topProducts = topProducts;
    }
    public List<Product> getTopProducts() {
        return topProducts;
    }*/
    private String name;
    private Long totalQuantity;
    private String code;

    public TopProductsDTO(String name, Long totalQuantity, String code) {
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public String getCode() {
        return code;
    }
}
