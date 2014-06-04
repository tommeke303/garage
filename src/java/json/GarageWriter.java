/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import Domain.BMW;
import Domain.Garage;
import Domain.Renault;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.Consumes;
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
@Consumes(MediaType.APPLICATION_JSON)
public class GarageWriter implements MessageBodyWriter<Garage> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Garage.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Garage t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Garage t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonObjectBuilder obj = Json.createObjectBuilder();
        if (t.getNaam() != null) {
            obj.add("naam", t.getNaam());
        }
        if (t.getWagensBMW().size() > 0) {
            JsonArrayBuilder arrBMW = Json.createArrayBuilder();
            for (BMW b : t.getWagensBMW()) {
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
            obj.add("BMW", arrBMW);
        }

        if (t.getWagensRenault().size() > 0) {
            JsonArrayBuilder arrRenault = Json.createArrayBuilder();
            for (Renault r : t.getWagensRenault()) {
                JsonObjectBuilder objRenault = Json.createObjectBuilder();
                objRenault.add("nummerplaat", r.getNummerplaat());
                if (r.getSerienummer() != null) {
                    objRenault.add("serienummer", r.getSerienummer());
                }
                if (r.getType() != null) {
                    objRenault.add("type", r.getType());
                }
                if (r.getKleur() != null) {
                    objRenault.add("kleur", r.getKleur());
                }
                arrRenault.add(objRenault);
            }
            obj.add("Renault", arrRenault);
        }
        
        try (JsonWriter writer = Json.createWriter(entityStream)){
            writer.writeObject(obj.build());
        }

    }
}
