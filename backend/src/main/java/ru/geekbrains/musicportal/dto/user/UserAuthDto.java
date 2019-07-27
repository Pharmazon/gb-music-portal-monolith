package ru.geekbrains.musicportal.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.geekbrains.musicportal.marker.UserAuthViews;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class UserAuthDto implements Serializable {

    @NotBlank
    @Size(min = 3, max = 60)
    @JsonView(UserAuthViews.All.class)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    @JsonView(UserAuthViews.All.class)
    private String password;

}
