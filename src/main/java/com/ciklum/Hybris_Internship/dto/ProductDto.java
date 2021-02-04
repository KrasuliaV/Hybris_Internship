package com.ciklum.Hybris_Internship.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private long id;

    @NotBlank(message = "The 'name' cannot be empty")
    private String name;

    private int orderCount;

    private int totalQuantity;


}
