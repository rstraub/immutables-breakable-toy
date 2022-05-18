package nl.codecraftr.java.kata;

import org.immutables.value.Value;

@Value.Immutable
public interface WikiPage {
    @Value.Parameter(order = 1)
    @Value.Default
    default boolean isActive() {
        return true;
    }

    @Value.Derived
    @Value.Auxiliary
    default boolean isInactive() {
        return !isActive();
    }

    @Value.Parameter(order = 0)
    String author();
}
