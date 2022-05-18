package nl.codecraftr.breakabletoy.immutables;

import org.immutables.value.Value;

@Value.Style(
        // Generate construction method using all attributes as parameters
        allParameters = true,
        // Changing generated name just for fun
        typeImmutable = "*Tuple",
        // We may also disable builder
        defaults = @Value.Immutable(builder = false))
public @interface Tuple {
}
