package com.kognitiv.offermanagement.configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //offers
    @Bean
    public Docket offersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(studentAPIInfo()).select()
                .apis(RequestHandlerSelectors.any()).paths(offersPaths())
                .build().
                        securitySchemes(Arrays.asList(basicAuth()));
    }

    private Predicate<String> offersPaths() {
        return Predicates.or(
                regex("/collect/offer.*"),
                regex("/collect/offer.*"));
    }

    private ApiInfo studentAPIInfo() {
        return new ApiInfoBuilder().title("Kognitiv APIs")
                .description("APIs for Kognitiv Interview Process")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0").build();
    }

    private SecurityScheme basicAuth() {
        return new BasicAuth("Basic Authentication");
    }
}
