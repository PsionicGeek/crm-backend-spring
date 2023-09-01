package com.psionicgeek.crmbackendspring.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonFilter("brandsFilter")
public class ProductCategory  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    private String name;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;


    @Builder.Default
    @ManyToMany(targetEntity = ProductBrand.class,fetch = FetchType.EAGER)
    @JoinTable(name = "product_bran_category",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "brand_id")}
    )
    Set<ProductBrand> brands=new HashSet<>();


    public void addBrand(ProductBrand brand){
        System.out.println("Called for brand: " + brand.getName() + "and for category : " + this.getName() );
        this.brands.add(brand);
        brand.getProductCategories().add(this);
    }


    public void removeBrand(ProductBrand brand){
        this.brands.remove(brand);
        brand.getProductCategories().remove(this);
    }


    //Mapping
    //OneToMany
    //private Set<Product> Orders ;
}
