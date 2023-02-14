package eu.kartoffelquadrat.ama;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

  Map<String, String> questions = new LinkedHashMap<>();

  @PostMapping(path = "/ama/questions", consumes = "text/plain")
  public void askQuestion(@RequestBody String question) {
    System.out.println(question);
    questions.put(question, question);
  }

  @GetMapping("/ama/questions")
  public String getAllQuestions(HttpServletRequest request) {

    // reject if this request comes from a foreign machine.
    if (!request.getRemoteAddr().equals("127.0.0.1")) {
      return "Nope, this page is not for you!";
    }

    StringBuilder allQuestions = new StringBuilder("");
    for (String question : questions.values()) {
      allQuestions.append(" * ").append(question).append("</br>");
    }
    return allQuestions.toString();
  }
}
