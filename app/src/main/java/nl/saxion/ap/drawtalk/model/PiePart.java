package nl.saxion.ap.drawtalk.model;

/**
 * Created by ear01 on 18/02/16.
 */
public class PiePart {

    private String topic;
    private int percentage;
    private int color;

    public PiePart(String topic, int percentage,  int color) {
        this.topic = topic;
        this.percentage = percentage;
        this.color = color;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

