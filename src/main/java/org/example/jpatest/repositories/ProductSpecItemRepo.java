package org.example.jpatest.repositories;


import org.example.jpatest.dto.ProdDTO;
import org.example.jpatest.dto.ProductDTO;
import org.example.jpatest.dto.SpecValue;
import org.example.jpatest.entity.Product;
import org.example.jpatest.entity.ProductSpecItem;
import org.example.jpatest.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//@EnableJpaRepositories
public interface ProductSpecItemRepo extends JpaRepository<ProductSpecItem, Long> {

//    List<ProductProjection> findByProductSpecsValueIsIn(List <Long> valuesList, Pageable pageable);


////     Long getId();//
//    String getName();
//    String getDescription();
//    double getPrice();
//    double getRating();
//    double getShadowRating();

//    private Long id;
//    private String name;
//    private String description;
//    private double price;
//    private double rating;
//    private double shadowRating;

//    List<ProductProjection.SpecsItemsDTOInterface> getSpecItemList();
//
////    @EntityGraph(attributePaths = "products")
//
//
//        List<ProductProjection> findProductSpecItemByProductSpecsValueIDs(List <Long> valuesList, Pageable pageable);

//    @EntityGraph(attributePaths = "products")
//    Page<ProductProjection> getProductByProductSpecsValueID(@Param("values") List<Long> valuesList
//                    , Pageable pageable);


//    @Query(value = """
//            select distinct   new org.example.jpatest.dto.ProductDTO(
//                p.id ,
//                p.name ,
//                p.description,
//                p.price,
//                 p.rating,
//                 p.shadowRating )
//            from  productSpecItem pSI
//            join pSI.product p
//            join pSI.productSpecsValue pSV
//            where pSV.value in:values
//             """)
//
//    Page<ProdDTO> getProdDTOBySpecValueIDS(@Param("values") List<Long> valuesList
//            , Pageable pageable);




    List<ProductSpecItem> findAll();

    @Modifying
    @Query(value = "delete  from productSpecItem  p " +
                   "where p.product.id = :productId and p.productSpecsValue.valueId = :specValueId")
    void deleteProductSpecItem(@Param(value = "productId") Long productId , @Param(value = "specValueId") Long specValueId);

    @Query(value = "select p from productSpecItem item" +
                   " join item.product p " +
                   " join item.productSpecsValue spec " +
                   "where p.id = :productId and spec.valueId = :specValueId")
    Optional<Product> findProductSpecItem(@Param(value = "productId") Long productId , @Param(value = "specValueId") Long specValueId);
//    Optional<ProductSpecItem> findProductSpecItem(@Param(value = "productId") Long productId , @Param(value = "specValueId") Long specValueId);


//    @Query("SELECT b FROM Book b
//            join b.category c
//            join fetch b.category
//            where c.category = :category")



//////////////////////

//    @Query(value = """
//            select distinct  new com.example.productservice.dto.ProductDTO(
//                p.id ,
//                p.name ,
//                p.description,
//                p.price,
//                 p.rating,
//                 p.shadowRating )
//            from  productSpecItem pSI
//            join pSI.product p
//            join pSI.productSpecsValue pSV
//            where pSV.id in:values
//             """,
//            countQuery = "select count(distinct p.id) from productSpecItem pSI join pSI.product p join pSI.productSpecsValue pSV where pSV.id in:values")
//    Page<ProductDTO> getProductDTOBySpecValueIDS(@Param("values") List<Long> valuesList
//            ,Pageable pageable);


    ///////////////////////////


    @Query(value = """
            select  count (distinct  p.id)
            from  productSpecItem pSI
            join pSI.product p
            join pSI.productSpecsValue pSV
            where pSV.valueId in:values
             """)

    Page<Product> getProductDTOBySpecV(@Param("values") List<Long> valuesList
            , Pageable pageable);


//    .setHint(QueryHints.HINT_FETCH_SIZE, pageSize)
    @Query(value = "select new org.example.jpatest.dto.ProductDTO(" +
                   "p.id,p.name,p.description,p.price,p.rating,p.shadowRating)" +
                   "from productSpecItem  psi " +
                   "join psi.product p " +
                   "join psi.productSpecsValue v " +
                   "where v.valueId in :values")
        Page<ProductDTO> getProductDTOBySpecValueIDS(@Param("values") List<Long> valuesList, Pageable pageable);
//        List<ProductDTO> getProductDTOBySpecValueIDS(@Param("values") List<Long> valuesList, Pageable pageable);

//    ........................



    @Query(value = "select new org.example.jpatest.dto.ProductDTO(" +
                   "p.id,p.name,p.description,p.price,p.rating,p.shadowRating)" +
                   "from productSpecItem psi " +
                   "join psi.product p " +
                   "join psi.productSpecsValue v " +
                   "where v.valueId in :values",
    countQuery = "select count (distinct p.id)" +
                 "from productSpecItem psi " +
                 "join psi.product p " +
                 "join psi.productSpecsValue v " +
                 "where v.valueId in :values")
Page<ProductDTO> getProductDTOBySpecValueIDSd(@Param("values") List<Long> valuesList, Pageable pageable);


//    @Query(value = """
//            select distinct  new org.example.jpatest.dto.ProductDTO(
//                p.id ,
//                p.name ,
//                p.description,
//                p.price,
//                 p.rating,
//                 p.shadowRating )
//            from  productSpecItem pSI
//            join pSI.product p
//            join pSI.productSpecsValue pSV
//            where pSV.id in:values
//             """)
//    Page<ProductDTO> getProductDTOBySpecValueIDS(@Param("values") List<Long> valuesList
//            ,Pageable pageable);
//    ..................................
    @Query(value = """
            select  new org.example.jpatest.dto.ProductDTO(
                p.id ,
                p.name ,
                p.description,
                p.price,
                 p.rating,
                 p.shadowRating )
            from  productSpecItem pSI
            join pSI.product p
            join pSI.productSpecsValue pSV
            where pSV.valueId in:values
            AND p.price >= :minPrice
            AND p.price <= :maxPrice
             """)
    Page<ProductDTO> getProductDTOBySpecValueIDSortByWithMinMaxPrice(@Param("values") List<Long> valuesList
            ,@Param("minPrice") int minPrice ,@Param("maxPrice")int maxPrice , Pageable pageable);



    @Query(value = """
            select new org.example.jpatest.dto.SpecValue(
            pV.valueId
            ,pV.value)
            from productSpecItem pSI
            join pSI.productSpecsValue pV
            join pSI.product p
            where p.id =:id
            """)
    List<SpecValue> getSpecValueByProductIdJ(@Param("id")Long productId);


    @Query(value = """
            select new org.example.jpatest.dto.SpecValue(
            pV.valueId
            ,pV.value)
            from productSpecItem pSI
            join pSI.productSpecsValue pV
            where pSI.product.id =:id
            """)
    List<SpecValue> getSpecValueByProductId(@Param("id")Long productId);
    List<ProductSpecItem> getProductSpecItemByProductId(Long id);

//    @Query(value = """
//            select  new org.example.jpatest.dto.ProductDTO(
//                p.id ,
//                p.name ,
//                p.description,
//                p.price )
//            from  productSpecItem pSI
//            join pSI.product p
//            join pSI.productSpecsValue pSV
//            where pSV.value like :value
//             """)
//    Page<ProductDTO> getProductDTOBySearchString(@Param("value") String value, Pageable pageable);


//    %?1%"

    ////////////////////
//    @Query(value = """
//            select  new org.example.jpatest.dto.ProductDTO(
//                p.id ,
//                p.name ,
//                p.description,
//                p.price,
//                 p.rating,
//                 p.shadowRating )
//            from  products p
//            where p.name like %:value%
//             """)
//    Page<ProductDTO> getProductDTOBySearchString(@Param("value") String value, Pageable pageable);



    /////////////


    //  mb use this method search  between :minId and :maxId
    @Query(value = """
            SELECT new org.example.jpatest.dto.ProductDTO(
                p.id ,
                p.name ,
                p.description ,
                p.price,
                 p.rating,
                 p.shadowRating)
            FROM productSpecItem pCi
            JOIN pCi.product p
            JOIN pCi.productSpecsValue pCh
            WHERE pCh.value IN :values
            AND p.price between :minPrice and :maxPrice
            
            """)
    Page<ProductDTO> getProductDTOBySpecsValues(@Param("values") List<String> values, Pageable pageable
            ,@Param("minPrice") int minPrice ,@Param("maxPrice")int maxPrice);


    @Query(value = """
            SELECT new org.example.jpatest.dto.ProductDTO(
                p.id ,
                p.name ,
                p.description ,
                p.price,
                 p.rating,
                 p.shadowRating)
            FROM productSpecItem pCi
            JOIN pCi.product p
            JOIN pCi.productSpecsValue pCh
            WHERE pCh.value IN :values
            """)
    Page<ProductDTO> getProductDTOBySpecsValues(@Param("values") List<String> values,Pageable pageable);


}
