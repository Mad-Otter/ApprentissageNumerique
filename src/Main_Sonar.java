import data.SonarConverter;

public class Main_Sonar {

    public static void main(String[] args) {
        SonarConverter train = new SonarConverter("sonar.data", "$TRAIN");
        SonarConverter test = new SonarConverter("sonar.data", "$TEST");
        SonarConverter all = new SonarConverter();

        for(int i = 0; i < train.size(); i++) {
        	all.addElement(train.getElement(i));
        }

        for(int i = 0; i < test.size(); i++) {
        	all.addElement(test.getElement(i));
        }

        train.exportGinnet("sonar.train.txt");
        test.exportGinnet("sonar.test.txt");
        all.exportGinnet("sonar.all.txt");
    }
}
