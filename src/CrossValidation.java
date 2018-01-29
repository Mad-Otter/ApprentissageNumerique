import algorithms.Classifier;

import java.util.ArrayList;
import data.*;

public class CrossValidation {

    public void test(Classifier classifier, Data data) {
        int nb_sections = 5;
        int moy = 0;

        for (int no_test_section = 0; no_test_section < 5; no_test_section++) {
            Data train = data.getTrainData(nb_sections, no_test_section);
            Data test = data.getTestData(nb_sections, no_test_section);

            int good = 0;
            int bad = 0;

            for (int i = 0; i < test.size(); i++) {
                if (classifier.getConsensus(train, test.getElement(i)).equals(test.getElement(i).getLabel())) {
                    good++;
                } else {
                    bad++;
                }
            }

            System.out.println("- Test sur la partie (" + (no_test_section + 1) + "/" + nb_sections + "), bon résultats : " + good + "/" + (good + bad) + " (" + ((int)((good * 100.0) / (good + bad))) + "%)");
            moy += ((int)((good * 100.0) / (good + bad)));
        }

        System.out.println("Moyenne : " + ((int) (moy / nb_sections)) + "% de bonnes réponses.");
    }
}
