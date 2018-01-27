import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Data all = new Data("iris.data.txt");
        ArrayList<Data> bases = all.divide(5);


        for (int K = 3; K <= 8; K++) {
            int scoreGood = 0;
            int scoreBad = 0;

            for (int i = 0; i < bases.get(0).size(); i++) {
                Element el = bases.get(0).getElement(i);

                for (int b = 1; b < 5; b++) {
                    if (bases.get(1).getConsensus(el, K).equals(el.getLabel())) {
                        scoreGood++;
                    } else {
                        scoreBad++;
                    }
                }
            }

            System.out.println("K = " + K + " - Bon résultats : " + scoreGood + "/" + (scoreGood+scoreBad) + " (" + ((int)((scoreGood*100.0)/(scoreGood+scoreBad))) + "%)");
        }
    }
}
