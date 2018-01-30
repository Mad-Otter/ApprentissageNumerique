package algorithms;

import data.Data;
import data.Element;

public class Kohonen implements Classifier{
    Data trainingData;
    Element[][] map;
    double sigma;
    double alpha;

    public Kohonen(){
        map = new Element[8][8];
    }

    private void initMap() {
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                map[i][j] = new Element(new float[]{(float)Math.random(),(float)Math.random(),(float)Math.random(),(float)Math.random()},"");
            }
        }
    }

    @Override
    public String getConsensus(Data data, Element element) {
        if (!data.equals(trainingData))
            train(data);
        return null;
    }

    private void train(Data data) {
        initMap();
        double[][] activations = new double[map.length][map[0].length];
        double[][] voisinage = new double[map.length][map[0].length];
        double alphaZero = 0.1; // a modifier pour trouver des bonnes valeurs
        double sigmaZero = 0.9; // ça aussi
        for (int k = 0; k < 1000; k++) { // 1000 choisi arbitrairement
            Element element = data.getRandomElement();
            alpha = alphaZero * 10/(10+k);
            sigma = sigmaZero * 10/(10+k);
            double min = Double.MAX_VALUE;
            int[] winner = new int[2];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    activations[i][j] = element.getEuclideanDistance(map[i][j]);
                    if (activations[i][j] < min) {
                        min = activations[i][j];
                        winner[0] = i;
                        winner[1] = j;
                    }
                }
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    voisinage[i][j] = gaussian(Math.abs(winner[0]-i)+Math.abs(winner[1]-j));
                    for(int n = 0; n < map[i][j].getVectorSize(); n++)
                        map[i][j].addValueToVector(n, (float) (alpha*voisinage[i][j]*(element.getVectorValue(n)-map[i][j].getVectorValue(n))));
                }
            }
        }


        // TODO utiliser les k-NN ici sur les éléments de la map pour changer les labels
    }


    public double gaussian(double x) {
        x /= sigma;
        return Math.exp(-x*x / 2) / Math.sqrt(2 * Math.PI) / sigma;
    }

}
