package com.invillia.acme;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json", "application/xml"));
    private static final Contact DEFAULT_CONTACT = new Contact("Thadeu Melo","github","thadeu.afm@gmail.com");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Thadeu Api Documentation",
            "This is a test",
            "3.14",
            "www.mytermsofservice.com",
            DEFAULT_CONTACT,
            "Apache",
            "www.licenseurl.com"
    );
    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

}

