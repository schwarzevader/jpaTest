package org.example.jpatest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SpecItemDTO {

    private String name;
    private boolean checked;
}
