package ru.geekbrains.musicportal.dto.http;

import lombok.Getter;
import lombok.Setter;
import ru.geekbrains.musicportal.util.ConcurrentDateFormat;

import java.util.Date;

@Getter
@Setter
public class ResponseWrapper {

    private int code;

    private String phraseText;

    private Object data;

    private String date;

    public ResponseWrapper(Response response, Object data) {
        this.code = response.getCode();
        this.data = data;
        this.phraseText = response.getReasonPhrase();
        this.date = ConcurrentDateFormat.format(new Date());
    }
}
