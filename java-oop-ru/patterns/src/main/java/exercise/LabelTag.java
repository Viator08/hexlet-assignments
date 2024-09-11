package exercise;

public class LabelTag implements TagInterface {
    private final String value;
    private final TagInterface tag;

    public LabelTag(String value, TagInterface tag) {
        this.value = value;
        this.tag = tag;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", value, tag.render());
    }
}
// END
