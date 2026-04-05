package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "oders_items")
public class Order_Iterm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private double ORIGINAL_PRICE;

    private double PRICE;
    
    private int QUANTITY;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ID_ORDER")
    @JsonBackReference
    private Orders order;



}
