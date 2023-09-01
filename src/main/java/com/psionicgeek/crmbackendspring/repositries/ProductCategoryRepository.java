package com.psionicgeek.crmbackendspring.repositries;

import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import com.psionicgeek.crmbackendspring.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {


}
