package org.example.jpatest.repositories.CRUD;


import org.example.jpatest.dto.ProductDTO;
import org.example.jpatest.entity.Product;
import org.example.jpatest.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource(path = "productCRUD")
public interface ProductCRUD extends JpaRepository<Product,Long> {


//    @EntityGraph(attributePaths = "products")
//    Page<ProductProjection>  getProdu( List<Long> valuesList
//            , Pageable pageable);
//
//
//    @EntityGraph(attributePaths = "products")
//    Page<ProductProjection> getProductDTOByProductSpecsValueID(@Param("values") List<Long> valuesList
//            , Pageable pageable);

    Product findProductById(Long aLong);
//    Optional<Product> findProductById(Long aLong);
}
