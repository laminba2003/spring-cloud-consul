package com.spring.training.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class ApplicationConfig {

    @Bean
    public OpenAPI openAPI(ConfigurableEnvironment environment, BuildProperties buildProperties, GitProperties gitProperties) {
        String version = "1.0";
        if (buildProperties != null && gitProperties != null) {
            version = buildProperties.getVersion() + "-" + gitProperties.getShortCommitId() + "-" + gitProperties.getBranch();
        }
        return new OpenAPI()
                .info(new Info().title(environment.getProperty("info.application.name"))
                        .description(environment.getProperty("info.application.description"))
                        .version(version));
    }
}
