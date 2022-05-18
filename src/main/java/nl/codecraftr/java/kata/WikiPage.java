package nl.codecraftr.java.kata;

import org.immutables.value.Value;

@Value.Immutable
public interface WikiPage {
    @Value.Parameter(order = 1)
    boolean isActive();

    @Value.Parameter(order = 0)
    String author();
}
