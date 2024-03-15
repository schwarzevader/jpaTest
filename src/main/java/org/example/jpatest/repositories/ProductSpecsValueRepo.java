package org.example.jpatest.repositories;


import org.example.jpatest.entity.ProductSpecsValue;
import org.example.jpatest.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductSpecsValueRepo extends JpaRepository<ProductSpecsValue, Long> {




//    @EntityGraph(attributePaths = "products")
//    Page<ProductProjection> findProductSpecsValueIn(List<Long> valuesList
//            , Pageable pageable);

//    @Query(value = "select p from productCharacteristic p where p.id LIKE:name")
//    public ProductCharacteristic find(@Param("name") Long id);

   // @Query(value = "select p from productCharacteristic p WHERE p.id LIKE:name")
//     ProductSpecsValue findProductCharacteristicById(Long id);












}
