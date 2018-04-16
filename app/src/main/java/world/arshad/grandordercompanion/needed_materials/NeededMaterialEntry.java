package world.arshad.grandordercompanion.needed_materials;


public class NeededMaterialEntry {

    private int count;
    private String text;

    public NeededMaterialEntry(int count, String text) {
        this.count = count;
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
