package org.emamotor.hammock.practice.api;

import org.emamotor.hammock.practice.TestConfigBean;
import org.emamotor.hammock.practice.model.Greeting;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ws.ament.hammock.core.annotations.ApplicationConfig;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GreetingResourceTest {

  @Deployment
  public static JavaArchive createArchive() {
    return HammockPracticeDeployment.deployment();
  }

  @Inject
  @ApplicationConfig
  private TestConfigBean config;

  @Before
  public void setUp() throws Exception {
    CDI.current().getBeanManager().fireEvent(new ContainerInitialized());
  }

  @Test
  public void echo() throws Exception {
    String message = "Hammock";
    String value = ClientBuilder.newClient()
      .target("http://" + config.getBindAddress() + ":" + config.getPort()).path(config.getContextRoot() + "/greetings/echo")
      .queryParam("message", message)
      .request().get(String.class);
    assertThat(value, is("Hi, " + message));
  }

  @Test
  public void hello() throws Exception {
    Greeting hello = new Greeting(2, "Hello", "こんにちは");

    Greeting value = ClientBuilder.newClient()
      .target("http://" + config.getBindAddress() + ":" + config.getPort()).path(config.getContextRoot() + "/greetings/2")
      .request().get(Greeting.class);
    assertThat(value, is(hello));
  }

  @Test
  public void all_greetings() throws Exception {
    String value = ClientBuilder.newClient()
      .target("http://" + config.getBindAddress() + ":" + config.getPort()).path(config.getContextRoot() + "/greetings/")
      .request().get(String.class);
    assertThat(value, is(
      "[" +
        "{" +
        "\"id\":1," +
        "\"english\":\"Good Morning\"," +
        "\"japanese\":\"おはよう\"" +
        "}," +
        "{" +
        "\"id\":2," +
        "\"english\":\"Hello\"," +
        "\"japanese\":\"こんにちは\"" +
        "}," +
        "{" +
        "\"id\":3," +
        "\"english\":\"Good Evening\"," +
        "\"japanese\":\"こんばんは\"" +
        "}" +
      "]"));
  }

}