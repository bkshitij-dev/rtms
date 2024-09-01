package com.example.rtms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        return new OpenAPI().info(info);
    }
}
