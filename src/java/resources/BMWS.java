/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resources;

import Domain.BMW;
import Domain.Garage;
import Domain.Renault;
import Domain.Wagen;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author thomas
 */
@Path("garages/{id}/BMW")
@Transactional(dontRollbackOn = {BadRequestException.class, NotFoundException.class})
public class BMWS {
    
    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private Validator validator;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BMW> getBMWS(@PathParam("id") String id){
        int idG = Integer.valueOf(id);
        Garage g = em.find(Garage.class, idG);
        if (g == null){
            throw new NotFoundException("garage niet gevonden.");
        }
        List<BMW> list = g.getWagensBMW();
        return list;
    }
    
    @GET
    @Path("{nummerplaat}")
    @Produces(MediaType.APPLICATION_JSON)
    public BMW getBMW(@PathParam("id")String id, @PathParam("nummerplaat") String nummerplaat){
        int idG = Integer.valueOf(id);
        Garage g = em.find(Garage.class, idG);
        
        if (g == null){
            throw new NotFoundException("garage niet gevonden");
        }
        
        
        BMW b = em.find(BMW.class, nummerplaat);
        if (b == null || !g.getWagensBMW().contains(b)){
            throw new BadRequestException("De garage bevat ofwel geen wagen met deze nummerplaat of er is geen wagen met deze nummerplaat");
        }
        
        return b;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGarage(@PathParam("id")String id, BMW b){
        int idG = Integer.valueOf(id);
        Garage g = em.find(Garage.class, idG);
        if (g == null){
            throw new NotFoundException("garage niet gevonden");
        }
        
        if (em.find(Wagen.class, b.getNummerplaat()) != null){
            throw new BadRequestException("Deze nummerplaat is al toegekend.");
        }
        
        Set<ConstraintViolation<BMW>> violation = validator.validate(b);
        if (!violation.isEmpty()){
            throw new BadRequestException("invoer was niet juist");
        }
        b.setGARAGE_ID(idG);
        em.detach(g);
        em.persist(b);
        g.addWagenBMW(b);
        em.merge(g);
        
        return Response.created(URI.create("/" + b.getNummerplaat())).build();
    }
    
    @DELETE
    @Path("{id}")
    public void removeGarage(@PathParam("id")String id){
        int idG = Integer.valueOf(id);
        Garage g = em.find(Garage.class, idG);
        
        if (g == null){
            throw new NotFoundException("garage niet gevonden");
        }
        
        if (!g.getWagensBMW().isEmpty()){
            for (BMW b:g.getWagensBMW()){
                em.remove(b);
            }
            g.setWagensBMW(null);
        }
        
        if (!g.getWagensRenault().isEmpty()){
            for (Renault r:g.getWagensRenault()){
                em.remove(r);
            }
            g.setWagensRenault(null);
        } 
        em.remove(g); 
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateGarage(@PathParam("id")String id, InputStream input){
        int idG = Integer.valueOf(id);
        Garage g = em.find(Garage.class, idG);
        
        if (g == null){
            throw new NotFoundException("garage niet gevonden");
        }
        
        em.detach(g);
        
        try (JsonReader reader = Json.createReader(input)){
            JsonObject obj = reader.readObject();
            
            g.setNaam(obj.getString("naam", null));
        }
        
        Set<ConstraintViolation<Garage>> violation = validator.validate(g);
        if (!violation.isEmpty()){
            throw new BadRequestException("Ongeldige invoer");
        }
        
        em.merge(g);
    }

    private Exception NotFoundException(String garage_niet_gevonden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
