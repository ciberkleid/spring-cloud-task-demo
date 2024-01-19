package com.example.springcloudtaskdemo;

//import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
class ContainersConfig {

    @Value("${my.testcontainers.reuse.enabled}")
    private boolean isReuseEnabled;

    @Bean
    @ServiceConnection
    //@RestartScope // DevTools auto-reload isn't suitable for tasks. Use ".withReuse(true)" instead.
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:16.1-alpine")
                .withReuse(isReuseEnabled); // Fix the port so that it is easier to access from psql CLI
    }

}
