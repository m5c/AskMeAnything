/**
 * Ip blocker implementation. Stores map of ip addresses with last moment of access. Grants access
 * only if last black call was more than a minute ago.
 *
 * @author Maximilian Schiedermeier.
 */

package eu.kartoffelquadrat.ama;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Ip Blocker Spring Component.
 */
@Component
public class IpBlocker {


  // Notes last moment of access per IP
  private Map<String, Long> lastAccess;

  /**
   * Public contructor used by Spring for bean creation.
   */
  public IpBlocker() {
    lastAccess = new LinkedHashMap<>();
  }

  /**
   * Adds entry to internal map for provied IP and current timestamp.
   *
   * @param ip as the string to block.
   */
  public void blockForOneMinute(String ip) {

    lastAccess.put(ip, System.currentTimeMillis());
  }

  /**
   * Checker to verify if an IP is currently allowed to access the service.
   *
   * @param ip as the stirng to check.
   * @return true if the ip is know an currenlty blocked.
   */
  public boolean isBlocked(String ip) {

    if (!lastAccess.containsKey(ip)) {
      return false;
    }

    // Check if last access was less than a minute ago
    return System.currentTimeMillis() - lastAccess.get(ip) < 60000;
  }
}
