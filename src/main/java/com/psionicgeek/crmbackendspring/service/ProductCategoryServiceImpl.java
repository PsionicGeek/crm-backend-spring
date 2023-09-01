package com.psionicgeek.crmbackendspring.service;

import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import com.psionicgeek.crmbackendspring.mapper.ProductCategoryMapper;
import com.psionicgeek.crmbackendspring.model.ProductCategoryDTO;
import com.psionicgeek.crmbackendspring.repositries.ProductBrandRepository;
import com.psionicgeek.crmbackendspring.repositries.ProductCategoryRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {



    private final ProductCategoryRepository categoryRepository;
    private final ProductBrandRepository brandRepository;




    private final ProductCategoryMapper mapper;


    @Override
    public List<ProductCategoryDTO> listProductCategory() {
        return categoryRepository.findAll().stream().map(mapper::productCategoryToProductCategoryDTO).toList();
    }

    @Override
    public ProductCategoryDTO addNewCategory(ProductCategoryDTO productCategoryDTO) {

        ProductCategory receivedCategory = mapper.productCategoryDTOToProductCategory(productCategoryDTO);
        if(receivedCategory.getBrands().isEmpty()){
            System.out.println("No Category is provided");
            return mapper.productCategoryToProductCategoryDTO(categoryRepository.save(receivedCategory));
        }

        ProductCategory mappedCategory=createAndMapCategory(receivedCategory);

        return mapper.productCategoryToProductCategoryDTO(mappedCategory);

    }


    private ProductCategory createAndMapCategory(ProductCategory productCategory){
        ProductCategory newCategory =ProductCategory.builder().name(productCategory.getName()).build();
        Set<ProductBrand> productBrands=  productCategory.getBrands();
        for (ProductBrand brand :
                productBrands) {
            if(brand.getId()!=null&&brandRepository.existsById(brand.getId())){

                newCategory.addBrand(brandRepository.findById(brand.getId()).get());
            }else{
                newCategory.addBrand(brandRepository.save(brand));
            }
        }
        return categoryRepository.save(newCategory);
    }

    @Override
    public ProductCategoryDTO findCategoryById(Integer id) {
        return mapper.productCategoryToProductCategoryDTO(categoryRepository.findById(id).get());
    }

    @Override
    public Boolean deleteById(Integer id) {
        if(categoryRepository.existsById(id)){
             categoryRepository.deleteById(id);
             return true;
        }else {
            return false;
        }

    }

    @Override
    public void addCategoryToBrand(Integer brandId, Integer categoryId) {
        ProductCategory category= categoryRepository.findById(categoryId).get();
        category.addBrand(brandRepository.findById(brandId).get());
        categoryRepository.save(category);
    }

    @Override
    public void removeCategoryToBrand(Integer brandId, Integer categoryId) {
        ProductCategory category= categoryRepository.findById(categoryId).get();
        category.removeBrand(brandRepository.findById(brandId).get());
        categoryRepository.save(category);

    }
}
