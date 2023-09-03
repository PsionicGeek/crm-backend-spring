package com.psionicgeek.crmbackendspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psionicgeek.crmbackendspring.entity.Product;
import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Data
public class ProductCategoryDTO {

    private Integer id;
    private String name;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    Set<ProductBrand> brands;
    Set<Product> products;

}
