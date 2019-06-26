package ru.geekbrains.musicportal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:connection.properties")
public class ServerPortConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(Integer.parseInt(serverPort));
    }

}
