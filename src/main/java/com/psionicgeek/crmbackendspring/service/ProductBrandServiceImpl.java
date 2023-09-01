package com.psionicgeek.crmbackendspring.service;

import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import com.psionicgeek.crmbackendspring.mapper.ProductBrandMapper;
import com.psionicgeek.crmbackendspring.model.ProductBrandDTO;
import com.psionicgeek.crmbackendspring.repositries.ProductBrandRepository;

import com.psionicgeek.crmbackendspring.repositries.ProductCategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductBrandServiceImpl implements ProductBrandService {

    private final ProductBrandRepository brandRepository;

    private final ProductCategoryRepository categoryRepository;

    private final ProductBrandMapper mapper;

    private final EntityManager entityManager;


    @Override
    public List<ProductBrandDTO> listProductBrand() {
        List<ProductBrandDTO> productBrandDTOS = brandRepository.findAll().stream().map(mapper::productBrandToProductBrandDTO).toList();
        System.out.println(productBrandDTOS.get(0).getName());
        return productBrandDTOS;
    }

    @Override
    public ProductBrandDTO addNewBrand(ProductBrandDTO productBrandDTO) {
        ProductBrand receivedBrand = mapper.productBrandDTOToProductBrand(productBrandDTO);
        if(productBrandDTO.getProductCategories().isEmpty()){
            System.out.println("No Categories are provided");
            return mapper.productBrandToProductBrandDTO(brandRepository.save(receivedBrand));
        }

        ProductBrand mappedBrand=createAndMapBrand(receivedBrand);

        return mapper.productBrandToProductBrandDTO(mappedBrand);


    }

    @Override
    public ProductBrandDTO getBrandById(Integer id) {
        return mapper.productBrandToProductBrandDTO(brandRepository.findById(id).get());
    }

    private ProductBrand createAndMapBrand(ProductBrand productBrand){
        ProductBrand newBrand =ProductBrand.builder().name(productBrand.getName()).build();
        Set<ProductCategory> categories=  productBrand.getProductCategories();
        for (ProductCategory category :
                categories) {
            if(category.getId()!=null&&categoryRepository.existsById(category.getId())){

                newBrand.addCategory(categoryRepository.findById(category.getId()).get());
            }else{
                newBrand.addCategory(categoryRepository.save(category));
            }
        }
        return brandRepository.save(newBrand);
    }



    @Override
    @Transactional
    public Boolean deleteById(Integer id) {
        if(brandRepository.existsById(id)){
            entityManager.createQuery("DELETE FROM brands c WHERE c.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            entityManager.close();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void addCategoryToBrand(Integer brandId, Integer categoryId) {
       ProductBrand brand= brandRepository.findById(brandId).get();
        brand.addCategory(categoryRepository.findById(categoryId).get());
        brandRepository.save(brand);
    }

    @Override
    public void removeCategoryToBrand(Integer brandId, Integer categoryId) {
        ProductBrand brand= brandRepository.findById(brandId).get();
        brand.removeCategory(categoryRepository.findById(categoryId).get());
        brandRepository.save(brand);
    }
}
