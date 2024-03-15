package org.example.jpatest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
public class SpecKey implements Serializable {
    private Long id;

    private String name;
    private  boolean showAll;

    public SpecKey(Long id, String name) {
        this.id = id;
        this.name = name;
        this.showAll=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecKey specKey = (SpecKey) o;
        return Objects.equals(id, specKey.id) && Objects.equals(name, specKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SpecKey{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", showAll=" + showAll +
               '}';
    }
}
