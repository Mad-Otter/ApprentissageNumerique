public class ElementDistance implements Comparable<ElementDistance> {
    private Element element;
    private float distance;

    public ElementDistance(Element element, float distance) {
        this.element = element;
        this.distance = distance;
    }

    public Element getElement() {
        return element;
    }

    public float getDistance() {
        return distance;
    }

    @Override
    public int compareTo(ElementDistance o) {
        if (o.getDistance() < getDistance()) {
            return 1;
        } else if (o.getDistance() > getDistance()) {
            return -1;
        }

        return 0;
    }
}
