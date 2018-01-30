import algorithms.Classifier;
import algorithms.IRIS_NN;
import data.Data;
import data.Iris;

public class Main_Template {

    public static void main(String[] args) {
        Data data = new Iris();
        Classifier classifier = new IRIS_NN();
        CrossValidation crossValidation = new CrossValidation();
        crossValidation.test(classifier, data);
    }
}
