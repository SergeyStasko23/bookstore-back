package ru.bookstore.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;

@Component("corsFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppCorsFilter extends CorsFilter {
    public AppCorsFilter() {
        super(configurationSource());
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(36000L);
        config.setAllowedMethods(
                Arrays.asList(GET.name(), POST.name(), PUT.name(), DELETE.name(), HEAD.name(), PATCH.name(), OPTIONS.name())
        );

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
