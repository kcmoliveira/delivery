package br.com.kleston.projects.delivery.model.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convertFromJson(String json, Class<T> target) {
        try {
            return JsonUtils.objectMapper.readValue( json, target );
        } catch (IOException e) {
            throw new RuntimeException( String.format( "Error converting from JSON[%s] to %s.", json, target ) );
        }
    }

    public static String convertToJson(Object object) {
        try {
            return JsonUtils.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( object );
        } catch (IOException e) {
            throw new RuntimeException( "Error converting object to JSON." );
        }
    }
}