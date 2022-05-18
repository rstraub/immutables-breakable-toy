package nl.codecraftr.breakabletoy.immutables;

import org.immutables.value.Value;

@Value.Immutable @Tuple
public interface Authors {
    String author();
    String coauthor();
}
