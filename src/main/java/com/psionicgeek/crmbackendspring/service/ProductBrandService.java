package com.psionicgeek.crmbackendspring.service;

import com.psionicgeek.crmbackendspring.model.ProductBrandDTO;
import com.psionicgeek.crmbackendspring.model.ProductCategoryDTO;

import java.util.List;

public interface ProductBrandService {

    List<ProductBrandDTO> listProductBrand();
    ProductBrandDTO addNewBrand(ProductBrandDTO productBrandDTO);

    ProductBrandDTO getBrandById(Integer id);

    Boolean deleteById(Integer id);

    void addCategoryToBrand(Integer brandId,Integer categoryId);

    void removeCategoryToBrand(Integer brandId, Integer categoryId);
}
