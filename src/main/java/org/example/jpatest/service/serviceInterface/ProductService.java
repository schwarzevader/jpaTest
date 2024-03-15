package org.example.jpatest.service.serviceInterface;



import org.example.jpatest.dto.SpecValue;
import org.example.jpatest.dto.Specs;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Map<String, List<SpecValue>> fromListToMap(List<Specs> list);


}
