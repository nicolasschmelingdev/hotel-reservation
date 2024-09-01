package com.ntconsult.hotelreservation.config;

import net.kaczmarzyk.spring.data.jpa.swagger.springdoc.SpecificationArgResolverSpringdocOperationCustomizer;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setPropertyCondition(context -> context.getSource() != null);
        return modelMapper;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SpecificationArgumentResolver());
    }

    @Bean
    public SpecificationArgResolverSpringdocOperationCustomizer specificationArgResolverSpringdocOperationCostmizer() {
        return new SpecificationArgResolverSpringdocOperationCustomizer();
    }

}
