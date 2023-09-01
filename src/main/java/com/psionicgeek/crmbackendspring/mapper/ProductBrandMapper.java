package com.psionicgeek.crmbackendspring.mapper;

import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import com.psionicgeek.crmbackendspring.model.ProductBrandDTO;

import org.mapstruct.Mapper;

@Mapper
public interface ProductBrandMapper {


    ProductBrandDTO productBrandToProductBrandDTO(ProductBrand productBrand);

    ProductBrand productBrandDTOToProductBrand(ProductBrandDTO productBrandDTO);
}
