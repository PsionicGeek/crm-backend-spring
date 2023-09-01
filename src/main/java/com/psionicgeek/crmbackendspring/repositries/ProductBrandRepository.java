package com.psionicgeek.crmbackendspring.repositries;

import com.psionicgeek.crmbackendspring.entity.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Integer>  {


}
