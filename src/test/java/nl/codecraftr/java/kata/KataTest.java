package nl.codecraftr.java.kata;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;

public class KataTest {

  @Test
  void shouldUnitTest() {
    Kata.main(new String[0]);

    assertThat(true).isTrue();
  }
}
