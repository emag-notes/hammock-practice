package org.emamotor.hammock.practice.api;

import org.emamotor.hammock.practice.ApplicationConfigBean;
import org.emamotor.hammock.practice.TestConfigBean;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import ws.ament.hammock.core.WebServerLauncher;

/**
 * @author tanabe
 */
public class HammockPracticeDeployment {

  public static JavaArchive deployment() {
    return ShrinkWrap.create(JavaArchive.class, GreetingResourceTest.class.getSimpleName() + ".jar")
      .addPackages(true, WebServerLauncher.class.getPackage())
      .addAsManifestResource(new StringAsset("ws.ament.hammock.core.impl.ClassScannerExtension\n" +
        "org.jboss.resteasy.cdi.ResteasyCdiExtension"), "services/javax.enterprise.inject.spi.Extension")
      .addAsManifestResource(new StringAsset("\n" +
        "<beans xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\"\n" +
        "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
        "       xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee\n" +
        "\t\thttp://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd\"\n" +
        "       bean-discovery-mode=\"all\">\n" +
        "</beans>"), "beans.xml")
      .addPackages(true, Filters.exclude(ApplicationConfigBean.class), "org.emamotor.hammock.practice")
      .addClasses(TestConfigBean.class);
  }

}
