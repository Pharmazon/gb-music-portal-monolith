package ru.geekbrains.musicportal.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.entity.band.Band;

public class BandSpecs {

    public static Specification<Band> bandNameContains(String word) {
        return (Specification<Band>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + word + "%");
    }
}
