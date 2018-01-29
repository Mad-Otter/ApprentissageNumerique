package algorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;
import data.*;

public class KnnClassifier implements Classifier {
	private int K;

	public KnnClassifier(int k) {
		K = k;
	}

	public String getConsensus(Data data, Element element) {
		ArrayList<Element> list = getKNN(data, element, K);
        ArrayList<LabelScore> labels = new ArrayList<>();

        for (Element el : list) {
            LabelScore cls = null;

            for (LabelScore ls : labels) {
                if (ls.getLabel().equals(el.getLabel())) {
                    cls = ls;
                    break;
                }
            }

            if (cls == null) {
                cls = new LabelScore(el.getLabel());
                labels.add(cls);
            }

            cls.increment();
        }

        LabelScore max = labels.get(0);
        for (LabelScore ls : labels) {
            if (ls.getScore() > max.getScore()) {
                max = ls;
            }
        }

        return max.getLabel();
	}

    private ArrayList<Element> getKNN(Data data, Element e, int k) {
        ArrayList<Element> knn = new ArrayList<>();
        PriorityQueue<ElementDistance> q = new PriorityQueue<>();

        for (int i = 0; i < data.size(); i++) {
        	q.add(new ElementDistance(data.getElement(i), data.getElement(i).getManhattanDistance(e)));
        }

        for (int i = 0; i < Math.min(q.size(), k); i++) {
            knn.add(q.poll().getElement());
        }

        return knn;
    }
}