package com.ciklum.Hybris_Internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


public interface ProductDto {

    String  getName();

    Integer getCount();

    Integer getSum_quant();
   }
