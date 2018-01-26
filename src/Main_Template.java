import algorithms.Classifier;
import algorithms.DeepLearning;
import data.Data;
import data.Iris;

public class Main_Template {

    public static void main(String[] args) {
        Data data = new Iris();
        Classifier classifier = new DeepLearning();
        CrossValidation crossValidation = new CrossValidation();
        crossValidation.test(classifier, data);
    }
}
