package org.emamotor.hammock.practice.api;

import org.emamotor.hammock.practice.model.Greeting;
import org.emamotor.hammock.practice.repository.GreetingRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * @author tanabe
 */
@Path("/greetings")
@RequestScoped
public class GreetingResource {

  @Inject
  private GreetingRepository repository;

  @GET
  @Path("/echo")
  @Produces("text/plain")
  public String greet(@QueryParam("message") String message) {
    return String.format("Hi, %s", message);
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getById(@PathParam("id") int id) {
    Greeting greeting = repository.findById(id);

    if(greeting == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    return Response.ok(greeting, MediaType.APPLICATION_JSON).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Set<Greeting> getAll() {
    return repository.findAll();
  }

}
