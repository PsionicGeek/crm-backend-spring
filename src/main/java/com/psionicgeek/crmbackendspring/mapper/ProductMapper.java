package com.psionicgeek.crmbackendspring.mapper;


import com.psionicgeek.crmbackendspring.entity.Product;
import com.psionicgeek.crmbackendspring.model.ProductCategoryDTO;
import com.psionicgeek.crmbackendspring.model.ProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    Product productDTOtoProduct(ProductDTO productDTO);

    ProductDTO productToProductDTO(Product product);
}
