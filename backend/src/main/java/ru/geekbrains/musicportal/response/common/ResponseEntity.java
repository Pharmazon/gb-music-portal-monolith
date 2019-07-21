package ru.geekbrains.musicportal.response.common;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.geekbrains.musicportal.marker.CommonViews;

import java.io.Serializable;

@Getter
@Setter
@JsonView(CommonViews.General.class)
public class ResponseEntity implements Serializable {

    private ResponseEnum response;
    private int code;
    private String description;

    public ResponseEntity(ResponseEnum response) {
        this.response = response;
        this.code = response.getCode();
        this.description = response.getDescription();
    }
}
