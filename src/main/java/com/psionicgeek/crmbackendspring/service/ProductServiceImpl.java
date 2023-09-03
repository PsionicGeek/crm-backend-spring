package com.psionicgeek.crmbackendspring.service;


import com.psionicgeek.crmbackendspring.mapper.ProductMapper;
import com.psionicgeek.crmbackendspring.model.ProductDTO;
import com.psionicgeek.crmbackendspring.repositries.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper mapper;


    @Override
    public List<ProductDTO> listAllProduct() {
        return productRepository.findAll().stream().map(mapper::productToProductDTO).toList();
    }
}
