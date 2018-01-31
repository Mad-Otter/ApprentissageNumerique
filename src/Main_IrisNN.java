import algorithms.Classifier;
import algorithms.IRIS_NN;
import data.Data;
import data.Iris;

public class Main_IrisNN {

    public static void main(String[] args) {
        Data data = new Iris("iris.data.txt");
        Classifier classifier = new IRIS_NN();
        CrossValidation crossValidation = new CrossValidation();
        crossValidation.test(classifier, data);
        //classifier.getConsensus(data, data.getElement(0));
    }
}
