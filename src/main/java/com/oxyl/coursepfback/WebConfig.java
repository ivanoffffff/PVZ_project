package com.oxyl.coursepfback;


import com.oxyl.coursepfback.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.oxyl.coursepfback")
@Import(DatabaseConfig.class) // Importe votre configuration de base de données
public class WebConfig implements WebMvcConfigurer {

    // Configuration CORS pour permettre les requêtes du frontend
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // S'applique aux URL commençant par /api/
                .allowedOrigins("*") // Ou spécifiez l'URL de votre frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600); // Durée de mise en cache des configurations CORS (en secondes)
    }

    // Configuration des convertisseurs JSON
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

    // Autres configurations MVC si nécessaires
    // Par exemple, pour configurer des gestionnaires de vues, des intercepteurs, etc.
}