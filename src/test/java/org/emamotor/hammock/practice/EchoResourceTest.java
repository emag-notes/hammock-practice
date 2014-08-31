package org.emamotor.hammock.practice;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.junit.Test;
import org.junit.runner.RunWith;
import ws.ament.hammock.core.WebServerLauncher;
import ws.ament.hammock.core.annotations.ApplicationConfig;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class EchoResourceTest {

  @Deployment
  public static JavaArchive createArchive() {
    return ShrinkWrap.create(JavaArchive.class, EchoResourceTest.class.getSimpleName() + ".jar")
      .addPackages(true, WebServerLauncher.class.getPackage())
      .addAsManifestResource(new StringAsset("ws.ament.hammock.core.impl.ClassScannerExtension\n" +
        "org.jboss.resteasy.cdi.ResteasyCdiExtension"),"services/javax.enterprise.inject.spi.Extension")
      .addAsManifestResource(new StringAsset("\n" +
        "<beans xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\"\n" +
        "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
        "       xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee\n" +
        "\t\thttp://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd\"\n" +
        "       bean-discovery-mode=\"all\">\n" +
        "</beans>"),"beans.xml")
      .addClasses(TestConfigBean.class, EchoResource.class);
  }

  @Inject
  @ApplicationConfig
  private TestConfigBean config;

  @Test
  public void testEcho() throws InterruptedException {
    CDI.current().getBeanManager().fireEvent(new ContainerInitialized());
    String value = ClientBuilder.newClient()
                      .target("http://localhost:" + config.getPort()).path("/api/echo")
                      .request().get(String.class);
    assertThat(value, is("hello"));
  }

}