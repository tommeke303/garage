/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package json;

import Domain.BMW;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author thomas
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class BMWReader implements MessageBodyReader<BMW>{

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return BMW.class.isAssignableFrom(type);
    }

    @Override
    public BMW readFrom(Class<BMW> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        BMW b = new BMW();
        try (JsonReader reader = Json.createReader(entityStream)){
            JsonObject obj = reader.readObject();
            b.setNummerplaat(obj.getString("nummerplaat", null));
            b.setSerienummer(obj.getString("serienummer", null));
            b.setKleur(obj.getString("kleur", null));
            b.setType(obj.getString("type", null));
        }
        
        return b;
        
    }
    
}
