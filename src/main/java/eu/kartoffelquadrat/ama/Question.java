/**
 * Simple bean to represent questinos as stirng bundled with origin IP.
 *
 * @author Maximilian Schiedermeier
 */

package eu.kartoffelquadrat.ama;

import java.util.Objects;

/**
 * Question class represents bundle of IP as origin and question string.
 */
public class Question {

  // IP of the question poster
  private final String ip;

  // Question asked as string
  private final String question;

  /**
   * Constructor that sets final/immutable question values.
   *
   * @param ip       as the authors IP address.
   * @param question as the question asked.
   */
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
