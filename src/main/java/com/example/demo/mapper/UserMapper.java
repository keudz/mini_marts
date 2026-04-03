package com.example.demo.mapper;


import com.example.demo.dto.response.OrderResponceDTO;
import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigInteger;
import java.time.LocalDate;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
   UserResponDTO userToUserResponDTO(User user);

//  @Mapping(source = "DESCRIPTION" ,target = "des")
//  @Mapping(source = "TOTAL_AMOUNT", target = "total_amount")
//  @Mapping(source = "STATUS", target = "status")
//  @Mapping(source = "ID_USER", target = "idUser")
//  @Mapping(source = "ID_ORDER",target = "idOrder" )
//  OrderResponceDTO orderToOrderResponceDTO(Orders order);

}
