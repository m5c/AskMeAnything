package eu.kartoffelquadrat.ama;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

  private final IpBlocker ipBlocker;
  private final Set<Question> questions = new LinkedHashSet<>();

  public QuestionController(@Autowired IpBlocker ipBlocker) {
    this.ipBlocker = ipBlocker;
  }


  @PostMapping(path = "/ama/questions", consumes = "text/plain")
  public String askQuestion(@RequestBody String question, HttpServletRequest request) {

    String ip = request.getRemoteAddr();
    // Log IP and ban for a minute
    if (ipBlocker.isBlocked(ip)) {
      System.out.println("BLOCKED!");
      return "BLOCKED!";
    }

    ipBlocker.blockForAMinute(ip);
    questions.add(new Question(request.getRemoteAddr(), question));
    System.out.println(question);
    return "OK!";
  }

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
