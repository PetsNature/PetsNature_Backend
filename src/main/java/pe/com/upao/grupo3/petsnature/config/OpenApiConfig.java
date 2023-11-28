package pe.com.upao.grupo3.petsnature.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public GroupedOpenApi httpApi() {
            return GroupedOpenApi.builder()
                    .group("http")
                    .pathsToMatch("/**")
                    .build();
        }

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("jallains1@upao.edu.pe");
        contact.setName("PetsNature");
        contact.setUrl("https://petsnature-frontend.netlify.app/");

        License mitLicense = new License().name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("PetsNature Management API")
                .version("1.8")
                .contact (contact)
                .description("This API exposes endpoints to manage tutorials.")
                .termsOfService("https://petsnature-frontend.netlify.app/")
                .license (mitLicense);

        return new OpenAPI().info(info);
    }
}