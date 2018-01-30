package data;

public class Element {
    private float[] vector;
    private String label;

    public Element(String line) {
        String[] splitted = line.split(",");
        vector = new float[splitted.length - 1];

        for (int i = 0; i < splitted.length - 1; i++) {
            vector[i] = Float.parseFloat(splitted[i]);
        }

        label = splitted[splitted.length - 1];
    }

    public Element(float[] vector, String label) {
        this.vector = vector;
        this.label = label;
    }

    public void setLabel(String label){this.label = label;}

    public String getLabel() {
        return label;
    }

    public boolean isEmpty() {
        return vector.length == 0;
    }

    public float getVectorValue(int i) {
        return vector[i];
    }

    public void addValueToVector(int i, float mod){vector[i] += mod;}

    public int getVectorSize(){return vector.length;}

    public float getManhattanDistance(Element e) {
        float d = 0;

        for (int i = 0; i < vector.length; i++) {
            d += Math.abs(vector[i] - e.getVectorValue(i));
        }

        return d;
    }

    public float getEuclideanDistance(Element e) {
        float d = 0;

        for (int i = 0; i < vector.length; i++) {
            d += Math.pow(vector[i] - e.getVectorValue(i), 2);
        }

        return (float) Math.sqrt(d);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(label);
        sb.append(" <");

        if (!isEmpty()) {
            sb.append(vector[0]);
        }

        for (int i = 1; i < vector.length; i++) {
            sb.append(", ");
            sb.append(vector[i]);
        }

        sb.append(">]");

        return sb.toString();
    }

    public String toGinnetLine() {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i]);
            sb.append(",");
        }

        sb.append(label);

        return sb.toString();
    }
}
