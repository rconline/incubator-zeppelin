package org.apache.zeppelin.utils;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.io.IOException;

/**
 * Utility methods to be used in the case of kerberos enabled through JAAS
 */
public class JaasUtils {
  public static final String JAVA_LOGIN_CONFIG_PARAM = "java.security.auth.login.config";

  public static final String LOGIN_CONTEXT_SERVER = "ZeppelinServer";
  public static final String LOGIN_CONTEXT_CLIENT = "ZeppelinClient";
  public static final String SERVICE_NAME = "serviceName";

  /**
   * Construct a JAAS configuration object per Zeppelin jaas configuration file
   * @param loginContextName
   * @param key
   * @return JAAS configuration object
   */
  public static String jaasConfig(String loginContextName, String key) throws IOException {
    AppConfigurationEntry[] configurationEntries =
      Configuration.getConfiguration().getAppConfigurationEntry(loginContextName);
    if (configurationEntries == null) {
      String errorMessage = "Could not find a '" + loginContextName +
        "' entry in this configuration.";
      throw new IOException(errorMessage);
    }

    for (AppConfigurationEntry entry : configurationEntries) {
      Object val = entry.getOptions().get(key);
      if (val != null)
        return (String) val;
    }
    return null;
  }
}
