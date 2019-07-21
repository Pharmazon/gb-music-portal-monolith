package ru.geekbrains.musicportal.response.common;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import ru.geekbrains.musicportal.marker.CommonViews;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonView(CommonViews.General.class)
public class ResponseWrapper<T> implements Serializable {

    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private ResponseEntity response;
    private String message;
    private T payload;

    public ResponseWrapper(T payload,
                           HttpStatus httpStatus,
                           ResponseEnum response,
                           String message) {
        this.response = new ResponseEntity(response);
        this.httpStatus = httpStatus;
        this.payload = payload;
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public static <T> ResponseWrapper<T> build(T object,
                                               HttpStatus httpStatus,
                                               ResponseEnum responseEnum,
                                               String message) {
        return new ResponseWrapper<>(object, httpStatus, responseEnum, message);
    }

    public static <T> ResponseWrapper<T> ok(T object,
                                            ResponseEnum responseEnum) {
        return build(object, HttpStatus.OK, responseEnum, "SUCCESS");
    }

    public static <T> ResponseWrapper<T> notFound(ResponseEnum responseEnum) {
        return build(null, HttpStatus.NOT_FOUND, responseEnum, "NOT_FOUND");
    }

    public static <T> ResponseWrapper<T> success(ResponseEnum responseEnum) {
        return build(null, HttpStatus.OK, responseEnum, "SUCCESS");
    }

    public static <T> ResponseWrapper<T> error(String message) {
        return build(null, HttpStatus.INTERNAL_SERVER_ERROR, null, message);
    }
}
