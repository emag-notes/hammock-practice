package org.emamotor.hammock.practice;

import ws.ament.hammock.core.annotations.ApplicationConfig;
import ws.ament.hammock.core.api.WebServerConfiguration;
import ws.ament.hammock.core.impl.ClassScannerExtension;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

/**
 * @author tanabe
 */
@ApplicationConfig
@ApplicationScoped
public class ApplicationConfigBean implements WebServerConfiguration {

  @Inject
  private ClassScannerExtension classScannerExtension;

  @Override
  public int getPort() {
    return 18080;
  }

  @Override
  public String getContextRoot() {
    return "/api";
  }

  @Override
  public Collection<Class> getProviderClasses() {
    return this.classScannerExtension.getProviders();
  }

  @Override
  public Collection<Class> getResourceClasses() {
    return this.classScannerExtension.getResources();
  }

  @Override
  public String getBindAddress() {
    return "0.0.0.0";
  }

}
