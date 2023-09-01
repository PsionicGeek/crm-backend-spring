package com.psionicgeek.crmbackendspring.mapper;

import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import com.psionicgeek.crmbackendspring.model.ProductCategoryDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductCategoryMapper {

    ProductCategoryDTO productCategoryToProductCategoryDTO(ProductCategory productCategory);

    ProductCategory productCategoryDTOToProductCategory(ProductCategoryDTO productCategoryDTO);
}
