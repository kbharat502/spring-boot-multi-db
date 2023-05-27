package example.test.springbootmultidb.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import example.test.springbootmultidb.contacts.jackson.deserializer.AddressDTOTypeDeserializer;
import example.test.springbootmultidb.contacts.jackson.deserializer.PhoneDTOTypeDeserializer;
import example.test.springbootmultidb.contacts.jackson.serializer.AddressDTOTypeSerializer;
import example.test.springbootmultidb.contacts.jackson.serializer.PhoneDTOTypeSerializer;
import example.test.springbootmultidb.contacts.model.enums.AddressDTOType;
import example.test.springbootmultidb.contacts.model.enums.PhoneDTOType;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Map<Class<?>, JsonDeserializer<?>> deserializerMap = new HashMap<>();
        deserializerMap.put(AddressDTOType.class, new AddressDTOTypeDeserializer());
        deserializerMap.put(PhoneDTOType.class, new PhoneDTOTypeDeserializer());

        Map<Class<?>, JsonSerializer<?>> serializerMap = new HashMap<>();
        serializerMap.put(AddressDTOType.class, new AddressDTOTypeSerializer());
        serializerMap.put(PhoneDTOType.class, new PhoneDTOTypeSerializer());

        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .modules(new JavaTimeModule())
                .deserializersByType(deserializerMap)
                .serializersByType(serializerMap)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.updateConverter(converters, new MappingJackson2HttpMessageConverter(builder.build()));
        this.updateConverter(converters, new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
    }

    private void updateConverter(final List<HttpMessageConverter<?>> converters, final HttpMessageConverter<?> converter) {
        int index = -1;
        for (int i = 0; i < converters.size(); i++) {
            if (converter.getClass().isInstance(converters.get(i))) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            converters.set(index, converter);
        } else {
            converters.add(converter);
        }
    }

}
