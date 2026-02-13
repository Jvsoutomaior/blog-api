package dev.jvsmaior.blog_api.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog API")
                        .version("0.0.1-SNAPSHOT")
                        .description("REST API to manage personal Blog Posts, made with Spring Boot 4.")
                        .license(new License()
                                .name("GNU General Public License, Version 3")
                                .url("https://www.gnu.org/licenses/gpl-3.0.html"))
                        .contact(new Contact()
                                .name("João Vítor Motta Souto Maior")
                                .email("soutomaior950@gmail.com")
                                .url("https://github.com/jvsoutomaior")));
    }
}
