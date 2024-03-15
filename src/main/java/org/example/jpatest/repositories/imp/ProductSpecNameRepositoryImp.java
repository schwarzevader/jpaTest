package org.example.jpatest.repositories.imp;



import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.jpatest.dto.SpecKey;
import org.example.jpatest.dto.SpecValue;
import org.example.jpatest.dto.Specs;
import org.example.jpatest.entity.ProductSpecName;
import org.example.jpatest.projection.SpecsProjection;
import org.hibernate.graph.RootGraph;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductSpecNameRepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;
    private int i = 0;

//    private final String orderByPriceAsc="order by p.price asc";
//    private final String orderByPriceDesc="order by p.price desc";
//    private final String orderByRating="order by p.rating desc";
//    private final String orderByShadowRating="order by p.shadowRating desc";

//    public List<ProductSpecsNamesDTO> getProductSpecsNamesDTOList(Long categoryId) {
//        return entityManager.createQuery(
//                        "select pSN.id ," +
//                                "pSN.name ," +
//                                "pSV.value ," +
//                                "pSV.id " +
//                                "from product_spec_name pSN " +
//                                "join pSN.productSpecValues pSV " +
//                                "join pSN.productCategory pC " +
//                                "where pC.id =: value")
//                .setParameter("value", categoryId)
//                .unwrap(org.hibernate.query.Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    ProductSpecsNamesDTO prodSpecNameDTO = new ProductSpecsNamesDTO();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getValues().add(new ProductSpecValueDTO((String) tuples[2], (Long) tuples[3]));
//                    return prodSpecNameDTO;
//                }).getResultList();
//    }


    public Map<SpecKey, List<SpecValue>> getProductSpecsNamesDTOMap(Long categoryId) {

//        EntityGraph<ProductSpecName> graph = entityManager.createEntityGraph(ProductSpecName.class);
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("ProductSpecName.productSpecValues");

        Map<SpecKey, List<SpecValue>> specsListMap = new HashMap<>();
        entityManager.createQuery(
                        "select  pSN.id ," +
                        "pSN.name ," +
                        "pSV.value ," +
                        "pSV.id " +
                        "from product_spec_name pSN " +
                        "join pSN.productSpecValues pSV " +

                        "where pSN.productCategory.id =: value")
//                        "where pC.id =: value")
                .setParameter("value", categoryId)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {
                    SpecKey tempSpec = new SpecKey((long) tuples[0], (String) tuples[1]);
                    if (specsListMap.containsKey(tempSpec)) {
                        specsListMap.get(tempSpec).add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                    } else {
                        SpecKey prodSpecNameDTO = new SpecKey((long) tuples[0], (String) tuples[1]);
                        SpecValue specValue = new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]);
                        List<SpecValue> list = new ArrayList<>();
                        list.add(specValue);
                        specsListMap.put(prodSpecNameDTO, list);
                    }
//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3],(String) tuples[1]));

//                    System.out.println("tuples------1--"+tuples[0] );
//                    System.out.println("tuples------2--"+tuples[1] );
//                    System.out.println("tuples------3--"+tuples[2] );
//                    System.out.println("tuples------4--"+tuples[3] );
                    System.out.println("count-----------------------------" + i);


                    i++;
                    return null;
                })
                .getResultList();

        return specsListMap;
    }

    public List<Specs> getProductSpecsNamesDTOList(Long categoryId) {


//        EntityGraph<ProductSpecName> graph = entityManager.createEntityGraph(ProductSpecName.class);
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("ProductSpecName.productSpecValues");

//        return entityManager.createQuery(
//                        "select distinct  pSN.id ," +
//                        "pSN.name ," +
//                        "pSV.value ," +
//                        "pSV.id " +
//                        "from product_spec_name pSN " +
//                        "join pSN.productSpecValues pSV " +
//
//                        "where pSN.productCategory.id =: value")
////                        "where pC.id =: value")
//                .setParameter("value", categoryId)
////                .setHint("javax.persistence.fetchgraph",entityGraph)
//                .unwrap(Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3],(String) tuples[1]));
//                    return prodSpecNameDTO;
//                }).getResultList();


//        left join product_categories pc1_0 on pc1_0.product_category_id=psn1_0.product_category_id
//        left join product_spec_value psv1_0 on psn1_0.product_spec_name_id=psv1_0.product_spec_name_id



//        "select  pSN.id  ," +
//        " pSN.name  ," +
////           "v.productSpecName.id ," +
//        " v.id," +
//        " v.value " +
//        "from product_spec_name pSN " +
//        "left join pSN.productSpecValues v " +
//        "left join pSN.productCategory pC " +
////           "left join pSN.productSpecValues v on pSN.id = v.productSpecName.id " +
////           "left join pSN.productCategory pC on pSN.productCategory.id = pC.id " +
//        "where pC.id = :categoryId"



        return entityManager.createQuery(
                        "select distinct pSN.id, pSN.name, pSV.value, pSV.id " +
                        "from product_spec_name  pSN " +
                        "left join   pSN.productSpecValues pSV on pSN.id = pSV.productSpecName.id "+
                        "left join  pSN.productCategory pC on pSN.productCategory.id=pC.id " +
                        "where pC.id = :value")

                .setParameter("value", categoryId)
                .unwrap(Query.class)
                .setResultTransformer((tuples, aliases) -> {
                    Specs prodSpecNameDTO = new Specs();
                    prodSpecNameDTO.setId((Long) tuples[0]);
                    prodSpecNameDTO.setName((String) tuples[1]);
                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                    return prodSpecNameDTO;
                })
                .getResultList();

//        return  null;
    }






//    public List<ProductSpecsNamesDTO> getProductSpecsNamesDTOListByProductId(Long productId) {
//        return entityManager.createQuery(
//                        "select pSN.id ," +
//                        "pSN.name ," +
//                        "pSV.value ," +
//                        "pSV.id " +
//                        "from product_spec_name pSN " +
//                        "join pSN.productSpecValues pSV " +
//                        "join pSV.productSpecItemList pSI " +
//                        "join pSI.product p " +
//                        "where p.id =: value")
//                .setParameter("value", productId)
//                .unwrap(org.hibernate.query.Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    ProductSpecsNamesDTO prodSpecNameDTO = new ProductSpecsNamesDTO();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getValues().add(new ProductSpecValueDTO((String) tuples[2], (Long) tuples[3]));
//                    return prodSpecNameDTO;
//                }).getResultList();
//    }




    public List<Specs> getProductSpecsNamesDTOListByProductId(Long productId) {
//        return entityManager.createQuery(
//                        "select pSN.id ," +
//                        "pSN.name ," +
//                        "pSV.value ," +
//                        "pSV.id " +
//                        "from product_spec_name pSN " +
//                        "join pSN.productSpecValues pSV " +
//                        "join pSV.productSpecItemList pSI " +
//                        "join pSI.product p " +
//                        "where p.id =: value")
//                .setParameter("value", productId)
//                .unwrap(org.hibernate.query.Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getValues().add(new SpecValue((String) tuples[2], (Long) tuples[3],(String) tuples[1]));
//                    return prodSpecNameDTO;
//                }).getResultList();
        return  null;
    }



    public List<Specs> getProductSpecsNamesDTOList(String searchString) {
//        return entityManager.createQuery(
//                        "select pSN.id ," +
//                                "pSN.name ," +
//                                "pSV.value ," +
//                                "pSV.id " +
//                                "from product_spec_name pSN " +
//                                "join pSN.productSpecValues pSV " +
//                                "where pSV.value =: value")
//                .setParameter("value", searchString)
//                .unwrap(org.hibernate.query.Query.class)
//                .setTupleTransformer((tuples, aliases) -> {
//                    Specs prodSpecNameDTO = new Specs();
//                    prodSpecNameDTO.setId((long) tuples[0]);
//                    prodSpecNameDTO.setName((String) tuples[1]);
//                    prodSpecNameDTO.getValues().add(new SpecValue(true,(String) tuples[2], (Long) tuples[3],(String)tuples[1]));
//                    return prodSpecNameDTO;
//                }).getResultList();
        return  null;
    }

//    public List<ProductDTO> getProductDTO(List<Long> valuesList
//            ,  int minPrice , int maxPrice ){
//        int totalPage;
//        int listSize;
//        Query query = entityManager.createQuery
//                ("select  new com.example.productservice.dto.ProductDTO(\n" +
//                "                p.id ,\n" +
//                "                p.name ,\n" +
//                "                p.description,\n" +
//                "                p.price )\n" +
//                "            from  productSpecItem pSI\n" +
//                "            join pSI.product p\n" +
//                "            join pSI.productSpecsValue pSV\n" +
//                "            where pSV.id in:values\n" +
//                "            AND p.price >= :minPrice\n" +
//                "            AND p.price <= :maxPrice\n" +
//                "            order by p.price asc")
//                .setParameter("values",valuesList)
//                .setParameter("minPrice",minPrice)
//                .setParameter("maxPrice",maxPrice);
//        int maxCount = query.getMaxResults();
//
//    }




    public Map<SpecKey, List<SpecValue>> getProductSpecs(List <Long> brandID) {




        Map<SpecKey, List<SpecValue>> specsListMap = new HashMap<>();
        entityManager.createQuery(
                        "select distinct pSN.id ," +
                        "pSN.name ," +
                        "pSV.value ," +
                        "pSV.id " +
                        "from product_spec_name pSN " +
                        "join pSN.productSpecValues pSV " +
                        "join pSV.productSpecItemList psi " +
                        "where psi.product.id in : value")
//                        "where pC.id =: value")
                .setParameter("value", brandID)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {
                    SpecKey tempSpec = new SpecKey((long) tuples[0], (String) tuples[1]);
                    if (specsListMap.containsKey(tempSpec)) {
                        specsListMap.get(tempSpec).add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                    } else {
                        SpecKey prodSpecNameDTO = new SpecKey((long) tuples[0], (String) tuples[1]);
                        SpecValue specValue = new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]);
                        List<SpecValue> list = new ArrayList<>();
                        list.add(specValue);
                        specsListMap.put(prodSpecNameDTO, list);
                    }
                    return null;
                })
                .getResultList();

        return specsListMap;
    }



    public List<Specs> getProductSpecsList(List <Long> brandID) {

       return  entityManager.createQuery(
                        "select distinct pSN.id ," +
                        "pSN.name ," +
                        "pSV.value ," +
                        "pSV.id " +
                        "from product_spec_name pSN " +
                        "join pSN.productSpecValues pSV " +
                        "join pSV.productSpecItemList psi " +
                        "where psi.product.id in : value")
                .setParameter("value", brandID)
                .unwrap(Query.class)
                .setTupleTransformer((tuples, aliases) -> {
                    Specs prodSpecNameDTO = new Specs();
                    prodSpecNameDTO.setId((Long) tuples[0]);
                    prodSpecNameDTO.setName((String) tuples[1]);
                    prodSpecNameDTO.getProductSpecValues().add(new SpecValue((String) tuples[2], (Long) tuples[3], (String) tuples[1]));
                    return prodSpecNameDTO;
                })
                .getResultList();


    }








    }



