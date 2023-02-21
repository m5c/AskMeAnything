/**
 * Web controller to return the web frontend for the asking a qeustion form.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.ama;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Web controller class. Spring maintained.
 */
@Controller
public class WebHandler {

  /**
   * Request mapping to landing page. Returns webUI.
   *
   * @return template origin for landing page.
   */
  @RequestMapping("/")
  public String forwardToLanding() {

    return "ask";
  }
}
