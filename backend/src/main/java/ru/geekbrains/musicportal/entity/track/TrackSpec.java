package ru.geekbrains.musicportal.entity.track;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.entity.common.SpecFeature;

public class TrackSpec {

    public static Specification<Track> getSpecification(SpecFeature specFeature){
        if (specFeature.getOperation().equalsIgnoreCase("%")){
            return (Specification<Track>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(specFeature.getFieldName()), "%" + specFeature.getValue() + "%");
        }else if (specFeature.getOperation().equalsIgnoreCase("=")){
            return (Specification<Track>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(specFeature.getFieldName()), specFeature.getValue());
        }else if (specFeature.getOperation().equalsIgnoreCase("~")){
            return (Specification<Track>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isMember(specFeature.getValue(), root.get(specFeature.getFieldName()));
        }
        return null;
    }
}
