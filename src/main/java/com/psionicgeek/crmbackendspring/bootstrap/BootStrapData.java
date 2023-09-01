package com.psionicgeek.crmbackendspring.bootstrap;

import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import com.psionicgeek.crmbackendspring.repositries.ProductBrandRepository;
import com.psionicgeek.crmbackendspring.repositries.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductBrandRepository productBrandRepository;
    @Override
    public void run(String... args) throws Exception {

        loadCategoryData();

    }
    void loadCategoryData(){

        ProductBrand saved= productBrandRepository.save(ProductBrand.builder().name("Something").build());
        ProductCategory productCategory1= ProductCategory.builder().id(1).name("Biscuit").build();
        ProductCategory productCategory2= ProductCategory.builder().id(2).name("Makeup").build();
        ProductCategory productCategory3= ProductCategory.builder().id(3).name("Namken").build();
        productCategoryRepository.save(productCategory1);
        productCategoryRepository.save(productCategory2);
        productCategoryRepository.save(productCategory3);
        ProductCategory category = productCategoryRepository.findAll().get(0);
        ProductCategory category2 = productCategoryRepository.findAll().get(1);
        category.addBrand(saved);
        category2.addBrand(saved);
        productCategoryRepository.save(category);
        productCategoryRepository.save(category2);




    }
}
