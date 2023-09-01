package com.psionicgeek.crmbackendspring.service;

import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import com.psionicgeek.crmbackendspring.model.ProductCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductCategoryService {


    List<ProductCategoryDTO> listProductCategory();
    ProductCategoryDTO addNewCategory(ProductCategoryDTO productCategoryDTO);

    ProductCategoryDTO findCategoryById(Integer id);

    Boolean deleteById(Integer id);


    void addCategoryToBrand(Integer brandId, Integer categoryId);

    void removeCategoryToBrand(Integer brandId, Integer categoryId);
}
