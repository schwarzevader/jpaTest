package org.example.jpatest;

import lombok.extern.slf4j.Slf4j;
import org.example.jpatest.dto.*;
import org.example.jpatest.projection.ProductProjection;
import org.example.jpatest.repositories.*;
import org.example.jpatest.repositories.imp.ProductSpecItemRepoImp;
import org.example.jpatest.repositories.imp.ProductSpecNameRepositoryImp;
import org.example.jpatest.someResponse.ProductPaginationResp;
import org.example.jpatest.someResponse.ProductPaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class JpaTestApplication implements CommandLineRunner {


    @Autowired
    ProductCategoryRepo productCategoryRepo;

    @Autowired
    ProductSpecsValueRepo p;
//    ProductSpecItemRepo p;
//    ProductCRUD p;

    @Autowired

    ProductSpecItemRepoImp productSpecItemRepoImp;


    @Autowired
    ProductSpecNameRepositoryImp productSpecNameRepositoryImp;

    @Autowired
    ProductSpecsNameRepo productSpec;

    @Autowired
    ProductSpecItemRepo productSpecItemRepo;

    ProductRepository prod;
    public static void main(String[] args) {
        SpringApplication.run(JpaTestApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------------------");
//        productCategoryRepo.getProductSpecsNamesDTOList(2L).forEach(e-> System.out.println(e.getName()+"-----"+e.getId()));

//        List<Specs> specsList = productCategoryRepo.getProductSpecsNamesDTOList(2L).stream()
//                .map(t -> {
//                    Specs specs = new Specs();
//                    specs.setId(t.getId());
//                    specs.setName(t.getName());
//                    specs.setValues(t.getValues().stream()
//                            .map(svp -> new SpecValue(svp.getSpecValue(), svp.getId(), svp.getSpecName()))
//                            .collect(Collectors.toList()));
//                    return specs;
//                })
//                .toList();




//        productSpecNameRepositoryImp.getProductSpecsNamesDTOList(2L).forEach(System.out::println);
//        productSpecNameRepositoryImp.getProductSpecsNamesDTOMap(2L).
//        forEach((key, valueList) -> {
//            System.out.println("Key: " + key);
//            System.out.println("Values: ");
//            valueList.forEach(value -> System.out.println("  " + value.toString()));
//            System.out.println("--------------------------");
//        });;
//        productSpecItemRepoImp.getProductDTO(List.of(1017L,1039L)).forEach(System.out::println);
//        productSpecItemRepoImp.getProductDTO(List.of(4016L,4013L)).forEach(e-> System.out.println(e.getSpecItemList().toString()));

        Pageable page1 = PageRequest.of(0, 60);

//        ProductPaginationResp productPaginationResponse =productSpecItemRepoImp.getProductDTO(List.of(4006L,4016L),page1);
        ProductPaginationResp productPaginationResponse =productSpecItemRepoImp.getProductDTO(List.of(1049L),page1);

        productPaginationResponse.getProducts().forEach(System.out::println);
        System.out.println("size-------------------="+productPaginationResponse.getProducts().size());
        System.out.println("total elements="+productPaginationResponse.getTotalElements());
        System.out.println("total pages="+productPaginationResponse.getTotalPages());


//        Map<SpecKey, List<SpecValue>> map =productSpecNameRepositoryImp.getProductSpecs(productPaginationResponse.getProducts().stream()
//                .map(ProdDTO::getId)
//                .collect(Collectors.toList()));

        for (Map.Entry<SpecKey, List<SpecValue>> entry : productSpecNameRepositoryImp.getProductSpecs(productPaginationResponse.getProducts().stream()
                .map(ProdDTO::getId)
                .collect(Collectors.toList())).entrySet()) {
            SpecKey key = entry.getKey();
            List<SpecValue> values = entry.getValue();
            System.out.println("Key: " + key.getId() + ", " + key.getName());
            for (SpecValue value : values) {
                System.out.println("    Value: " + value.toString() +",");
            }
        }
//        map=productSpecNameRepositoryImp.getProductSpecs(productSpecItemRepoImp.getProductDTO(List.of(1049L,1096L),page1).getProducts().stream()
//                .map(ProdDTO::getId)
//                .collect(Collectors.toList()));

        for (Map.Entry<SpecKey, List<SpecValue>> entry : productSpecNameRepositoryImp.getProductSpecs(productSpecItemRepoImp.getProductDTO(List.of(1049L,1096L),page1).getProducts().stream()
                .map(ProdDTO::getId)
                .collect(Collectors.toList())).entrySet()) {
            SpecKey key = entry.getKey();
            List<SpecValue> values = entry.getValue();
            System.out.println("Key: " + key.getId() + ", " + key.getName());
            for (SpecValue value : values) {
                System.out.println("    Value: " + value.toString() +",");
            }
        }

//        map=productSpecNameRepositoryImp.getProductSpecs(productSpecItemRepoImp.getProductDTO(List.of(1049L,1096L,1058L),page1).getProducts().stream()
//                .map(ProdDTO::getId)
//                .collect(Collectors.toList()));

        for (Map.Entry<SpecKey, List<SpecValue>> entry : productSpecNameRepositoryImp.getProductSpecs(productSpecItemRepoImp.getProductDTO(List.of(1049L,1096L,1088L),page1).getProducts().stream()
                .map(ProdDTO::getId)
                .collect(Collectors.toList())).entrySet()) {
            SpecKey key = entry.getKey();
            List<SpecValue> values = entry.getValue();
            System.out.println("Key: " + key.getId() + ", " + key.getName());
            for (SpecValue value : values) {
                System.out.println("    Value: " + value.toString() +",");
            }
        }

//        List<Specs> specsList= productSpecNameRepositoryImp.getProductSpecsList(productPaginationResponse.getProducts().stream()
//                .map(ProdDTO::getId)
//                .collect(Collectors.toList()));
//
//
//        specsList.forEach(System.out::println);




//        List<Long> idList= new ArrayList<>();
//        idList.add(1008L);
//        idList.add(1018L);
//        System.out.println("total pages"+productSpecItemRepo.getProductDTOBySpecValueIDSd(idList,page1).getContent().toString());
//        productSpecItemRepo.getProductDTOBySpecValueIDS(idList,page1).forEach(System.out::println);

//       Page<ProductProjection> myPAge= p.findProductSpecsValueIn(List.of(1017L,1039L),page1);
//               .stream().map(t->{
//            return new ProdDTO(t.getId(),t.getName(),t.getDescription(),t.getPrice(),t.getRating(),t.getShadowRating(),
//                    t.getSpecItemList().stream().map(list->new SpecsItemsDTO(list.getId(),list.getQuantity())).collect(Collectors.toList()));
//        });


//        myPAge.getContent().stream()
//                .map(t->{
//            return new ProdDTO(t.getId(),t.getName(),t.getDescription(),t.getPrice(),t.getRating(),t.getShadowRating(),
//                    t.getSpecItemList().stream().map(list->new SpecsItemsDTO(list.getId(),list.getQuantity())).collect(Collectors.toList()));
//        }).forEach(System.out::println);


//        System.out.println(prod.findProductById(1000L).toString());

//        productSpec.findByProductCategoryId(2L,page1)
//                        .stream()
//                .map(t -> {
//                    Specs specs = new Specs();
//                    specs.setId(t.getId());
//                    specs.setName(t.getName());
//                    specs.setProductSpecValues(t.getProductSpecValues().stream()
//                            .map(svp -> new SpecValue(svp.getValue(),svp.getValueId(),t.getId()))
//                            .collect(Collectors.toList()));
//                    return specs;
//                })
//                .toList().forEach(System.out::println);


//        List<Specs> specsList = productSpec.findByProductCategoryId(2L)

//                .stream()
//                .map(t -> {
//                    Specs specs = new Specs();
//                    specs.setId(t.getId());
//                    specs.setName(t.getName());
//                    specs.setProductSpecValues(t.getProductSpecValues().stream()
//                            .map(svp -> new SpecValue(svp.value(),svp.valueId(),t.getId()))
//                            .collect(Collectors.toList()));
//                    return specs;
//                })
//                .toList();
//
////        List<Specs> specsList = productSpecNameRepositoryImp.getProductSpecsNamesDTOList(2L);
//        specsList.forEach(System.out::println);







    }
}
