package ru.geekbrains.musicportal.pojo;

import lombok.Data;
import ru.geekbrains.musicportal.enums.FilterJoinTypeEnum;

@Data
public class SpecFeature {

    private FilterJoinTypeEnum joinType;

    private String fieldName;

    private String operation;

    private Object value;

}
