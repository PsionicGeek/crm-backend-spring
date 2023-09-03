package com.psionicgeek.crmbackendspring.model;

import com.psionicgeek.crmbackendspring.entity.Product;
import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import java.util.Set;
@Builder
@Data
public class ProductBrandDTO {

    private int id ;

    private String name;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
    Set<ProductCategory> productCategories;
    Set<Product> products;
}
