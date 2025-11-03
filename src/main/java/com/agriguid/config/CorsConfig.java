package com.agriguid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@Configuration
public class CorsConfig {
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowCredentials(true);
        
        // Add your Vercel domains
        config.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "https://project-c6r2ks2cc-gouthams-projects-95181b18.vercel.app",
            "https://*.vercel.app"  // Allow all Vercel preview URLs
        ));
        
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setExposedHeaders(Arrays.asList("Authorization"));
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}