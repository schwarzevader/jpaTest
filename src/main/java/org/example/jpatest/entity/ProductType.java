package org.example.jpatest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table( name = "product_type")
//@Table(schema = "public", name = "product_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {

    @Id
    @Column(name = "product_type_id",unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private long sortValue;

    private String value;
    @OneToMany(	mappedBy = "productType",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProductCategory> productCategoryList = new ArrayList<>();
}
