package nl.codecraftr.breakabletoy.immutables;

/**
 * Kata Main Class.
 */
public class ImmutablesBreakableToy {

    /**
     * Cli runner.
     */
    public static void main(String[] args) {
        var samplePage = WikiPageValue.builder()
                .author("CodeCraftr")
                .isActive(true)
                .addTags("tools", "java", "fp")
                .build();

        System.out.println("Wiki page using immutables:");
        System.out.println("---------------------------");
        System.out.println(samplePage);
    }
}
