package ru.geekbrains.musicportal.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUrl {
    public static String serverUrl;
    public static String apiPath;
    public static String trackPath;

    public ConfigUrl(
            @Value("${backend.server}") String serverUrl,
            @Value("${backend.server.api.path}") String apiPath,
            @Value("${backend.server.api.track.path}") String trackPath
            ) {
        ConfigUrl.serverUrl = serverUrl;
        ConfigUrl.apiPath = apiPath;
        ConfigUrl.trackPath = trackPath;
    }
}
