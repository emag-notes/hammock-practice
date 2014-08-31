package org.emamotor.hammock.practice;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author tanabe
 */
@Path("/echo")
@RequestScoped
public class EchoResource {

  @GET
  @Produces("text/plain")
  public String greet() {
    return "hello";
  }

}
