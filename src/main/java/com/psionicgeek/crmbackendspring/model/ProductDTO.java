package com.psionicgeek.crmbackendspring.model;

import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Builder
@Data
public class ProductDTO {

    private Long id;
    private String name;
    private Integer totalQuantity;
    private Integer quantityInHand;
    private BigInteger price;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime expirationDate;
    private ProductBrand brand;
    private ProductCategory category;
}
