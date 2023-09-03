package com.psionicgeek.crmbackendspring.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "brands")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFilter("productCategoryFilter")
public class ProductBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private String name;


    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;


    @Builder.Default
    @ManyToMany(mappedBy = "brands",fetch = FetchType.EAGER)
    Set<ProductCategory> productCategories=new HashSet<>();

    public void addCategory(ProductCategory category){
        this.getProductCategories().add(category);
        category.getBrands().add(this);
    }


    public void removeCategory(ProductCategory category){
        this.productCategories.remove(category);
        category.getBrands().remove(this);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "brand",fetch = FetchType.EAGER)
    private Set<Product> products;


}
