package org.example.jpatest.projection;

import java.util.List;

public interface ProductProjection {
     Long getId();
//     Long getId();

    String getName();

     String getDescription();
     double getPrice();
    double getRating();
    double getShadowRating();

    List<SpecsItemsDTOInterface> getSpecItemList();

    interface SpecsItemsDTOInterface{
        long getId();
//        long getSpecsItemsId();
        long getQuantity();
    }
}
