package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonFileReader {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public JsonFileReader(){

    }


    public static String readJsonAsString(String resourcePath){

        try(InputStream InputStream = getResource(resourcePath)) {
            return new String(InputStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException exception) {
            throw new IllegalStateException("unable to read the resource " + resourcePath, exception);
        }
    }

    public static JsonNode readJsonAsNode(String resourcePath){

        try(InputStream InputStream = getResource(resourcePath)) {
            return OBJECT_MAPPER .readTree(InputStream);

        } catch (IOException exception) {
            throw new IllegalStateException("unable to parse the resource " + resourcePath, exception);
        }
    }
    public static <T> T readJsonAsObject (String resourcePath, Class<T> targetClass){

        try(InputStream InputStream = getResource(resourcePath)) {
            return JsonFileReader.OBJECT_MAPPER.readValue(InputStream, targetClass);

        } catch (IOException exception) {
            throw new IllegalStateException("unable to parse the resource " + resourcePath, exception);
        }
    }



    private static InputStream getResource(String resourcePath){

        InputStream InputStream = JsonFileReader.class.getClassLoader().getResourceAsStream(resourcePath);

        if (InputStream == null) {
            throw new RuntimeException(resourcePath + " not found");
        }

        return  InputStream ;

    }

}
