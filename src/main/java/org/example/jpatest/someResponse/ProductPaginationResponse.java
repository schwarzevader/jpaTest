package org.example.jpatest.someResponse;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.jpatest.dto.ProductDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ProductPaginationResponse {

    private int totalPages;
    private long totalElements;
    private List<ProductDTO> products;
}
