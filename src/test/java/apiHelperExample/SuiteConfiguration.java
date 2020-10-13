package apiHelperExample;

import org.aeonbits.owner.Config;

import java.io.IOException;
import java.util.Properties;

/**
 * Loads test suite configuration from resource files.
 */
public interface SuiteConfiguration extends Config {
  @Key("site.url")
  String siteUrl();

  @Key("github.url")
  String githubUrl();

  @Key("github.repositories.path")
  String githubRepositoriesPath();
}
