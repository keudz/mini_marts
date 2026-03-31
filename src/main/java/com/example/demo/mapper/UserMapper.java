package com.example.demo.mapper;


import com.example.demo.dto.response.UserResponDTO;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigInteger;
import java.time.LocalDate;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
   UserResponDTO userToUserResponDTO(User user);

}
