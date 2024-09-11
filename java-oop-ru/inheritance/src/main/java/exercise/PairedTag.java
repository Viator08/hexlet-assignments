package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    String value;
    List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String value, List<Tag> children) {
        super(name, attributes);
        this.value = value;
        this.children = children;
    }

    @Override
    public String toString() {
        String atr = attributes.entrySet()
                .stream()
                .map(e -> e.getKey() + "=\"" + e.getValue() + "\"")
                .collect(Collectors.joining(" "));


        return "<" + name + (atr.isEmpty() ? "" : " " + atr) + ">" + value + children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining("")) + "</" + name + ">";
    }
}
// END
