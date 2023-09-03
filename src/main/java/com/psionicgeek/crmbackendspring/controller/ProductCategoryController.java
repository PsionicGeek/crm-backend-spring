package com.psionicgeek.crmbackendspring.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.psionicgeek.crmbackendspring.customfilter.myFilters;
import com.psionicgeek.crmbackendspring.model.ProductCategoryDTO;
import com.psionicgeek.crmbackendspring.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductCategoryController {

    // TODO: 27-08-2023 implement proper http response when custom exception is implemented


    private final ProductCategoryService productCategoryService;


    @GetMapping("/categories")
    public MappingJacksonValue listProductCategory() throws JsonProcessingException {
        // TODO: 27-08-2023 Add auth check when security is done
        List<ProductCategoryDTO> somebean = productCategoryService.listProductCategory();
//        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.serializeAllExcept("productCategories");
//        FilterProvider provider =new SimpleFilterProvider().addFilter("productCategoryFilter",filter);
        MappingJacksonValue value = new MappingJacksonValue(somebean);
        value.setFilters(myFilters.createCustomFilterProvider("productCategoryFilter","productCategories"));
        return value ;

    }
    @GetMapping("/category/{id}")
    public MappingJacksonValue getCategoryById(@PathVariable Integer id){
        ProductCategoryDTO somebean = productCategoryService.findCategoryById(id);
        MappingJacksonValue value = new MappingJacksonValue(somebean);
//        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.serializeAllExcept("productCategories");
//        FilterProvider provider =new SimpleFilterProvider().addFilter("productCategoryFilter",filter);
        value.setFilters(myFilters.createCustomFilterProvider("productCategoryFilter","productCategories"));
        return value;

    }




    @PostMapping("/category")
    public ResponseEntity<String> addNewCategory(@RequestBody(required = true) ProductCategoryDTO productCategory){
        // TODO:Add auth check when security is done (Only admin can add category)
        ProductCategoryDTO dto = productCategoryService.addNewCategory(productCategory);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/category/{categoryId}/add/brand/{brandId}")
    public  ResponseEntity<String> addCategoryTOBrand(@PathVariable Integer brandId,@PathVariable Integer categoryId){

        productCategoryService.addCategoryToBrand(brandId,categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/category/{categoryId}/remove/brand/{brandId}")
    public  ResponseEntity<String> removeCategoryTOBrand(@PathVariable Integer brandId,@PathVariable Integer categoryId){

        productCategoryService.removeCategoryToBrand(brandId,categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
        // TODO: 27-08-2023 Implement auth check when security is implemented

        productCategoryService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
