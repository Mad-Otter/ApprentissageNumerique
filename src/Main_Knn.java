import java.util.ArrayList;
import data.*;
import algorithms.*;

public class Main_Knn {
    public static void main(String[] args) {
        Iris all = new Iris("iris.data.txt");
        
        CrossValidation cr = new CrossValidation();
        cr.test(new KnnClassifier(5), all);
    }
}
