package com.psionicgeek.crmbackendspring.controller;


import com.psionicgeek.crmbackendspring.customfilter.myFilters;

import com.psionicgeek.crmbackendspring.model.ProductBrandDTO;

import com.psionicgeek.crmbackendspring.model.ProductCategoryDTO;
import com.psionicgeek.crmbackendspring.service.ProductBrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ProductBrandController {

    private final ProductBrandService productBrandService;

    // TODO: 28-08-2023 Implement security and other stuff

    @GetMapping("/brands")
    MappingJacksonValue listProductBrand() {

        List<ProductBrandDTO> somebean = productBrandService.listProductBrand();

        MappingJacksonValue value = new MappingJacksonValue(somebean);



        value.setFilters(myFilters.createCustomFilterProvider("brandsFilter", "brands"));
        return value;
    }

    @PostMapping("/brand")
    public ResponseEntity<String> addNewBrand(@RequestBody(required = true) ProductBrandDTO productBrand) {
        // TODO:Add auth check when security is done (Only admin can add category)
        ProductBrandDTO dto = productBrandService.addNewBrand(productBrand);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping("/brands/{id}")
    MappingJacksonValue getBrandById(@PathVariable Integer id) {

        ProductBrandDTO foundBrand = productBrandService.getBrandById(id);

        MappingJacksonValue filteredBrand = new MappingJacksonValue(foundBrand);

        filteredBrand.setFilters(myFilters.createCustomFilterProvider("brandsFilter", "brands"));
        return filteredBrand;
    }

    @PutMapping("/brand/{brandId}/add/category/{categoryId}")
    public  ResponseEntity<String> addCategoryTOBrand(@PathVariable Integer brandId,@PathVariable Integer categoryId){

        productBrandService.addCategoryToBrand(brandId,categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/brand/{brandId}/remove/category/{categoryId}")
    public  ResponseEntity<String> removeCategoryTOBrand(@PathVariable Integer brandId,@PathVariable Integer categoryId){

        productBrandService.removeCategoryToBrand(brandId,categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Integer id) {
        // TODO: 27-08-2023 Implement auth check when security is implemented

        productBrandService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
