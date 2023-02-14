package eu.kartoffelquadrat.ama;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class IpBlocker {


  // Notes last moment of access per IP
  private Map<String, Long> lastAccess;

  public IpBlocker() {
    lastAccess = new LinkedHashMap<>();
  }

  public void blockForAMinute(String ip) {

    lastAccess.put(ip, System.currentTimeMillis());
  }

  public boolean isBlocked(String ip) {

    if(!lastAccess.containsKey(ip))
      return false;

    // Check if last access was less than a minute ago
    return System.currentTimeMillis() - lastAccess.get(ip) < 60000;
  }
}
