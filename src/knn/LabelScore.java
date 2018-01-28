package knn;

public class LabelScore implements Comparable<LabelScore> {
    private String label;
    private int score;

    public LabelScore(String l) {
        label = l;
        score = 0;
    }

    public void increment() {
        score++;
    }

    public String getLabel() {
        return label;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(LabelScore o) {
        if (o.getScore() < getScore()) {
            return 1;
        } else if (o.getScore() > getScore()) {
            return -1;
        }

        return 0;
    }
}
