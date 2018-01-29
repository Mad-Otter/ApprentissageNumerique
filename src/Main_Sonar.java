import data.SonarConverter;

public class Main_Sonar {

    public static void main(String[] args) {
        SonarConverter train = new SonarConverter("sonar.data", "$TRAIN");
        SonarConverter test = new SonarConverter("sonar.data", "$TEST");

        train.exportGinnet("sonar.train.txt");
        test.exportGinnet("sonar.test.txt");
    }
}
