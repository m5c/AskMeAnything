/**
 * Main Launcher class for ask me anything service.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.ama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class powers up Spring and ensures the annotated controllers are detected.
 *
 * @author Maximilian Schiedermeier
 */
@SpringBootApplication
public class RestLauncher {
  /**
   * Default constructor.
   */
  public RestLauncher() {
  }

  /**
   * Main program starter.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    SpringApplication.run(RestLauncher.class, args);
  }
}
