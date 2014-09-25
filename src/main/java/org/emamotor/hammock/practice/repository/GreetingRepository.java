package org.emamotor.hammock.practice.repository;

import org.emamotor.hammock.practice.model.Greeting;

import javax.inject.Singleton;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author tanabe
 */
@Singleton
public class GreetingRepository {

  private static final Map<Integer, Greeting> greetings = new ConcurrentHashMap<>();

  static {
    greetings.put(1, new Greeting(1, "Good Morning", "おはよう"));
    greetings.put(2, new Greeting(2, "Hello", "こんにちは"));
    greetings.put(3, new Greeting(3, "Good Evening", "こんばんは"));
  }

  public Greeting findById(int id) {
    return greetings.get(id);
  }

  public Set<Greeting> findAll() {
    return greetings.values().stream().collect(Collectors.toSet());
  }

}
