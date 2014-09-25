package org.emamotor.hammock.practice.model;

/**
 * @author tanabe
 */
public class Greeting {

  private int id;
  private String english;
  private String japanese;

  public Greeting() {}

  public Greeting(int id, String english, String japanese) {
    this.id = id;
    this.english = english;
    this.japanese = japanese;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEnglish() {
    return english;
  }

  public void setEnglish(String english) {
    this.english = english;
  }

  public String getJapanese() {
    return japanese;
  }

  public void setJapanese(String japanese) {
    this.japanese = japanese;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Greeting greeting = (Greeting) o;

    if (id != greeting.id) return false;
    if (english != null ? !english.equals(greeting.english) : greeting.english != null) return false;
    if (japanese != null ? !japanese.equals(greeting.japanese) : greeting.japanese != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (english != null ? english.hashCode() : 0);
    result = 31 * result + (japanese != null ? japanese.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Greeting{" +
      "id=" + id +
      ", english='" + english + '\'' +
      ", japanese='" + japanese + '\'' +
      '}';
  }

}
