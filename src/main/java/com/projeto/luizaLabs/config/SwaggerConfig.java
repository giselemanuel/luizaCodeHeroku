package com.projeto.luizaLabs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
        }

    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo("Luiza Code",
                "API REST desenvolvida para o projeto final no curso LuizaCode",
                "1.0",
                "Terms of service",
                 new Contact("Ana Beatriz Fontes / Endi Gama / Gisele Manuel / Lavinia Souza / Nelismy Baro / Tatiane Costa",
                         "","[fontesfbs, Idneamga8989, giselermanuel, laviniasouza1404, baronelismy, tatianecosta1995]@gmail.com"),
                "Apcahe License Version 2.0", "https://www.apache.org/license.html", new ArrayList<VendorExtension>());

        return apiInfo;
    }
}