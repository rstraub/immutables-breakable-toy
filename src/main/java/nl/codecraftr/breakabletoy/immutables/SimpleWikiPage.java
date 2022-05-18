package nl.codecraftr.breakabletoy.immutables;

import java.util.List;

record SimpleWikiPage(String author, boolean isActive, List<String> tags) {
}
