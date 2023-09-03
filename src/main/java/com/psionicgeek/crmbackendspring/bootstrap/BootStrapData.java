package com.psionicgeek.crmbackendspring.bootstrap;

import com.psionicgeek.crmbackendspring.entity.Product;
import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import com.psionicgeek.crmbackendspring.repositries.ProductBrandRepository;
import com.psionicgeek.crmbackendspring.repositries.ProductCategoryRepository;
import com.psionicgeek.crmbackendspring.repositries.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductBrandRepository productBrandRepository;

    private final ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {

        loadCategoryData();
        loadBrandData();
        loadProduct();

        loadProductBrandMapping();




    }
    void loadCategoryData(){
        ProductCategory productCategory1= ProductCategory.builder().id(1).name("Biscuit").build();
        ProductCategory productCategory2= ProductCategory.builder().id(2).name("Makeup").build();
        ProductCategory productCategory3= ProductCategory.builder().id(3).name("Namken").build();
        productCategoryRepository.save(productCategory1);
        productCategoryRepository.save(productCategory2);
        productCategoryRepository.save(productCategory3);
    }

    void  loadProductBrandMapping(){
        ProductBrand brand1=productBrandRepository.findAll().get(0);
        ProductBrand brand2 =productBrandRepository.findAll().get(1);
        ProductCategory category1 = productCategoryRepository.findAll().get(0);
        ProductCategory category2 = productCategoryRepository.findAll().get(1);
        ProductCategory category3=productCategoryRepository.findAll().get(2);
        category1.addBrand(brand1);
        category2.addBrand(brand1);
        category3.addBrand(brand2);
        productCategoryRepository.save(category1);
        productCategoryRepository.save(category2);
        productCategoryRepository.save(category3);
    }
    void loadBrandData(){
        ProductBrand brand1=ProductBrand.builder().name("Brand A").build();
        ProductBrand brand2=ProductBrand.builder().name("Brand B").build();
        ProductBrand brand3=ProductBrand.builder().name("Brand C").build();

        productBrandRepository.save(brand1);
        productBrandRepository.save(brand2);
        productBrandRepository.save(brand3);

    }



    public void loadProduct(){
        ProductBrand brand1=productBrandRepository.findAll().get(0);
        ProductCategory category1 = productCategoryRepository.findAll().get(0);
        ProductBrand brand2=productBrandRepository.findAll().get(1);
        ProductCategory category2 = productCategoryRepository.findAll().get(1);
        Product product1 = Product
                            .builder()
                            .name("Product1")
                            .price(BigInteger.valueOf(103L))
                            .totalQuantity(20)
                            .expirationDate(LocalDateTime.now().plusYears(1))
                            .quantityInHand(15)
                            .brand(brand1)
                            .category(category1)
                            .build();
        Product product2 = Product
                .builder()
                .name("Product2")
                .price(BigInteger.valueOf(103L))
                .totalQuantity(20)
                .expirationDate(LocalDateTime.now().plusYears(2))
                .quantityInHand(15)
                .brand(brand1)
                .category(category2)
                .build();

        Product product3 = Product
                .builder()
                .name("Product3")
                .price(BigInteger.valueOf(103L))
                .totalQuantity(20)
                .expirationDate(LocalDateTime.now().plusMonths(1))
                .quantityInHand(15)
                .brand(brand2)
                .category(category1)
                .build();
        Product product4 = Product
                .builder()
                .name("Product4")
                .price(BigInteger.valueOf(103L))
                .totalQuantity(20)
                .expirationDate(LocalDateTime.now().plusYears(1))
                .quantityInHand(15)
                .brand(brand2)
                .category(category2)
                .build();
        Product product5 = Product
                .builder()
                .name("Product5")
                .price(BigInteger.valueOf(103L))
                .totalQuantity(20)
                .expirationDate(LocalDateTime.now().plusYears(4))
                .quantityInHand(15)
                .brand(brand1)
                .category(category1)
                .build();
        Product product6 = Product
                .builder()
                .name("Product6")
                .price(BigInteger.valueOf(103L))
                .totalQuantity(20)
                .expirationDate(LocalDateTime.now().plusMonths(10))
                .quantityInHand(15)
                .brand(brand2)
                .category(category1)
                .build();

        productRepository.save(product1);

        System.out.println(product1);

    }
}
