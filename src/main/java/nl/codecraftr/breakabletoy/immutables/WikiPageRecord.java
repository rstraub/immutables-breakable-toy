package nl.codecraftr.breakabletoy.immutables;

import java.util.List;

record WikiPageRecord(String author, boolean isActive, List<String> tags) {
}
