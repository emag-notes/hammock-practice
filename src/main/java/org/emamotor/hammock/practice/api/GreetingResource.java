package org.emamotor.hammock.practice.api;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author tanabe
 */
@Path("/greeting")
@RequestScoped
public class GreetingResource {

  @GET
  @Produces("text/plain")
  public String greet() {
    return "hello";
  }

}
