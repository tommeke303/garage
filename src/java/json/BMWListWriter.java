/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import Domain.BMW;
import Domain.Garage;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author thomas
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class BMWListWriter implements MessageBodyWriter<List<BMW>> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (!List.class.isAssignableFrom(type)) {
            return false;
        }
        if (genericType instanceof ParameterizedType) {
            Type[] arguments = ((ParameterizedType) genericType).getActualTypeArguments();
            return arguments.length == 1 && arguments[0].equals(BMW.class);
        } else {
            return false;
        }
    }

    @Override
    public long getSize(List<BMW> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(List<BMW> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonArrayBuilder arrBMW = Json.createArrayBuilder();
        for (BMW b : t) {
            JsonObjectBuilder objBMW = Json.createObjectBuilder();
            objBMW.add("nummerplaat", b.getNummerplaat());
            if (b.getSerienummer() != null) {
                objBMW.add("serienummer", b.getSerienummer());
            }
            if (b.getType() != null) {
                objBMW.add("type", b.getType());
            }
            if (b.getKleur() != null) {
                objBMW.add("kleur", b.getKleur());
            }
            arrBMW.add(objBMW);
        }
        
        try (JsonWriter writer = Json.createWriter(entityStream)){
            writer.writeArray(arrBMW.build());
        }

    }
}
