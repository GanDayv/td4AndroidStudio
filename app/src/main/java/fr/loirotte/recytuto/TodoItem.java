package fr.loirotte.recytuto;

/**
 * Created by phil on 06/02/17.
 */

public class TodoItem {
    public enum Tags {
        Faible, Normal, Important
    }

    private String label;
    private Tags tag;
    private boolean done;

    public TodoItem(Tags tag, String label) {
        this.tag = tag;
        this.label = label;
        this.done = false;
    }

    public String getLabel() {
        return label;
    }

    public Tags getTag() {
        return tag;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setTag(Tags tag) {
        this.tag = tag;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
