package eu.kartoffelquadrat.ama;

import java.util.Objects;

public class Question {

  private final String ip;
  private final String question;

  public Question(String ip, String question) {
    this.ip = ip;
    this.question = question;
  }

  @Override
  public String toString() {
    return "IP='" + ip + ": " + question;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question1 = (Question) o;
    return Objects.equals(ip, question1.ip) && Objects.equals(question, question1.question);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ip, question);
  }
}
