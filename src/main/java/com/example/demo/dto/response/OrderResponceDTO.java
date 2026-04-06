package com.example.demo.dto.response;

import com.example.demo.entity.Order_Iterm;
import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponceDTO {

   private int idOrder;

   private String des;
   @JsonInclude(JsonInclude.Include.NON_DEFAULT)
   private String status;
   @JsonInclude(JsonInclude.Include.NON_DEFAULT)
   private double total_amount;

   private List<OrderItemListResponceDTO> orderItermList;
   @JsonInclude(JsonInclude.Include.NON_DEFAULT)
   private int idUser;

   @JsonInclude(JsonInclude.Include.NON_DEFAULT)
   private String userEmail;

   @JsonInclude(JsonInclude.Include.NON_DEFAULT)
   private String userPhone;

}

