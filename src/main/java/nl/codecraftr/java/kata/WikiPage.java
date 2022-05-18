package nl.codecraftr.java.kata;

import java.util.List;
import java.util.Optional;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(
        typeImmutable = "*Value"
)
public interface WikiPage {
    @Value.Parameter(order = 1)
    @Value.Default
    default boolean isActive() {
        return true;
    }

    @Value.Derived
    // Auxiliary excludes the property from generated methods like hashCode, toString and equals
    @Value.Auxiliary
    default boolean isInactive() {
        return !isActive();
    }

    @Value.Parameter(order = 0)
    String author();

    Optional<String> coauthor();

    // Implicitly gets a default empty collection
    List<String> tags();
}
