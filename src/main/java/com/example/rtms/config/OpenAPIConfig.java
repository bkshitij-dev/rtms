package com.example.rtms.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/*
 * @author Kshitij
 * @created 01-Sep-2024
 */
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("rtms@example.com");
        contact.setName("RTMS");
        contact.setUrl("https://www.rtms.com");

        Info info = new Info()
                .title("Restaurant Table Management System API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to Restaurant Table Management System");

        return new OpenAPI().info(info)
                .addSecurityItem(new SecurityRequirement().addList("RTMS"))
                .components(new Components()
                        .addSecuritySchemes("RTMS", new SecurityScheme()
                                .name("RTMS")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer").bearerFormat("JWT")));
    }

    @Bean
    public GlobalOpenApiCustomizer sortTagsAlphabetically() {
        return openAPI -> {
            List<Tag> tags = openAPI.getTags();
            if (tags != null) {
                tags.sort(Comparator.comparing(Tag::getName));
            }
        };
    }
}
