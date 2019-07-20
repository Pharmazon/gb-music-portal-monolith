package ru.geekbrains.musicportal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlConfig {

    public static String serverUrl;
    public static String apiPath;
    public static String trackPath;

    public UrlConfig(
            @Value("${backend.server}") String serverUrl,
            @Value("${server.servlet.context-path}") String apiPath,
            @Value("${backend.server.api.track.path}") String trackPath) {
        UrlConfig.serverUrl = serverUrl;
        UrlConfig.apiPath = apiPath;
        UrlConfig.trackPath = trackPath;
    }
}
