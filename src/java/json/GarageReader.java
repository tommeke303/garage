/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package json;

import Domain.Garage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
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
@Consumes(MediaType.APPLICATION_JSON)
public class GarageReader implements MessageBodyReader<Garage>{

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Garage.class.isAssignableFrom(type);
    }

    @Override
    public Garage readFrom(Class<Garage> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Garage g = new Garage();
        
        try (JsonReader reader = Json.createReader(entityStream)){
            JsonObject obj = reader.readObject();
            
            g.setNaam(obj.getString("naam", null));
            
        }
        
        return g;
    }
    
}
