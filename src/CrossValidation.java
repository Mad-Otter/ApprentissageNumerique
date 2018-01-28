import algorithms.Classifier;

import java.util.ArrayList;
import data.*;

public class CrossValidation {

    public void test(Classifier classifier, Data data) {
    	ArrayList<Data> bases = data.divide(5);

	    int scoreGood = 0;
        int scoreBad = 0;

        for (int i = 0; i < bases.get(0).size(); i++) {
            Element el = bases.get(0).getElement(i);

            for (int b = 1; b < 5; b++) {
                if (classifier.getConsensus(bases.get(1), el).equals(el.getLabel())) {
                    scoreGood++;
                } else {
                    scoreBad++;
                }
            }
        }

        System.out.println("Bon résultats : " + scoreGood + "/" + (scoreGood+scoreBad) + " (" + ((int)((scoreGood*100.0)/(scoreGood+scoreBad))) + "%)");
    }
}
