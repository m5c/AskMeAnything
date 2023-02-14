/**
 * Main REST controller for the AMA service.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.ama;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller definition for AMA service. Allows askeng questions and looking up list of all
 * questions.
 */
@RestController
public class QuestionController {

  // Injected bean responsible for blocking posters for one minute after each post.
  private final IpBlocker ipBlocker;

  // Collection storing all questions asked so far, including origin information.
  private final Set<Question> questions = new LinkedHashSet<>();

  /**
   * Bean contructor used by spring.
   *
   * @param ipBlocker as the blocker bean to inject.
   */
  public QuestionController(@Autowired IpBlocker ipBlocker) {
    this.ipBlocker = ipBlocker;
  }

  /**
   * REST enpoint to ask new questions. A coller can ask at most one question per minute. The IP is
   * blcoked in between.
   *
   * @param question as the string value of the question asked.
   * @param request  as the http bundle information on the origin.
   * @return A short status string indicating if request was accepted or rejected.
   */
  @PostMapping(path = "/ama/questions", consumes = "text/plain")
  public String askQuestion(@RequestBody String question, HttpServletRequest request) {

    String ip = request.getRemoteAddr();
    // Log IP and ban for a minute
    if (ipBlocker.isBlocked(ip)) {
      System.out.println("BLOCKED!");
      return "BLOCKED FOR A MINUTE!\r";
    }

    ipBlocker.blockForOneMinute(ip);
    questions.add(new Question(request.getRemoteAddr(), question));
    System.out.println(question);
    return "ACCEPTED!\r";
  }

  /**
   * REST endpoint to look up all questions asked so far. Only accessible from localhost.
   *
   * @param request as the http bundle information on the origin.
   * @return Either the list of all questions ar a rejection message.
   */
  @GetMapping("/ama/questions")
  public String getAllQuestions(HttpServletRequest request) {

    // reject if this request comes from a foreign machine.
    if (!request.getRemoteAddr().equals("127.0.0.1")) {
      return "Nope, this page is not for you!";
    }

    StringBuilder allQuestions = new StringBuilder("");
    for (Question question : questions) {
      allQuestions.append(question).append("</br>");
    }
    return allQuestions.toString();
  }
}
