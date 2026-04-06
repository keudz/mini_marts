package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {
    private double totalRevenue;
    private int totalOrders;
    private int totalProductsSold;
    private List<ProductSalesDTO> topSellingProducts;
    private List<ProductResponseDTO> lowStockProducts;
    private Map<String, Long> orderStatusSummary;
}