package org.example.jpatest.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "productSpecValue")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_spec_value")
@NamedEntityGraph(
        name = "ProductSpecsValue")
//@Table(schema = "public" ,name = "product_spec_value")
public class ProductSpecsValue implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_spec_value_id")
    private Long valueId;





//    @Id
//    @ManyToOne
//    private Person person;

    //    private String name;

    @Column(name = "value")
    private String value;





//    @OneToMany(	mappedBy = "productSpecs",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<ProductSpecItem> productSpecItemValue = new ArrayList<>();

    @OneToMany(	mappedBy = "productSpecsValue",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProductSpecItem> productSpecItemList = new ArrayList<>();

//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_spec_name_id")
//    @JoinColumn(name = "spec_name",
//            referencedColumnName = "spec_name")
    private ProductSpecName productSpecName;

    @Override
    public String toString() {
        return "ProductCharacteristic{" +
                '\'' +
                ", value='" + value + '\'' +
                '}';
    }




//    @ManyToOne(fetch = FetchType.LAZY)
//    private ProductCategory productCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductSpecsValue product = (ProductSpecsValue) o;
        return Objects.equals(valueId, product.valueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueId);
    }
}
