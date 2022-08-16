package com.alabvix.planet.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.VendorExtension
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
class SwaggerConfig {

    @Value("\${app.info.title}")
    private val title = String()

    @Value("\${app.info.description}")
    private val description = String()

    @Value("\${app.info.version}")
    private val version = String()

    @Value("\${app.info.license}")
    private val license = String()

    @Value("\${app.info.licenseUrl}")
    private val licenseUrl = String()

    @Value("\${app.info.termsOfServiceUrl}")
    private val termsOfServiceUrl = String()

    @Value("\${app.contact.name}")
    private val contactName = String()

    @Value("\${app.contact.email}")
    private val contactEmail = String()

    @Value("\${app.contact.url}")
    private val contactUrl = String()

    @Bean
    fun api(): Docket {
        // Expondo todas os endpoints na documentação
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .apiInfo(metaData())
    }

    private fun metaData():ApiInfo {
        val contact = Contact(contactName, contactUrl, contactEmail)
        return ApiInfo(
            title,
            description,
            version,
            termsOfServiceUrl,
            contact,
            license,
            licenseUrl,
            ArrayList<Any>() as Collection<VendorExtension<Any>>?
        )
    }



}