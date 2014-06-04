package resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * Deze klasse is de configuratie van JAX-RS. Door het overschrijven van de methoden van deze klasse
 * kunnen we allerlei zaken configureren. In onze oefeningen is dit niet nodig. Het enige wat hier
 * van belang is, is de annotatie @ApplicationPath. Deze geeft het prefix aan dat deel uitmaakt van
 * de URL van alle JAX-RS resources (klassen).
 */

@ApplicationPath("api")
public class Configuration extends Application
{

}
