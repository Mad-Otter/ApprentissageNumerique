package algorithms;

import data.*;

public class KnnClassifier implements Classifier {
	private int K;

	public KnnClassifier(int k) {
		K = k;
	}

	public String getConsensus(Data data, Element element) {
		Iris irisData = (Iris) data;
		return irisData.getConsensus(element, K);
	}
}